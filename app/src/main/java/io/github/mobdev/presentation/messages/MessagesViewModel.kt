package io.github.mobdev.presentation.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mobdev.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val repository: ChatRepository,
) : ViewModel() {

    private var currentChat: String? = null

    private var currentChannel: String? = null

    private var oldestMessageId: String? = null

    private val _uiState = MutableStateFlow(MessagesUiState())

    val uiState = _uiState.asStateFlow()

    fun loadChat(chat: String) {
        if (currentChat != chat) {
            _uiState.update {
                it.copy(sendMessageText = "")
            }
        }
        currentChat = chat
        loadMessages(chat)
    }

    private fun loadMessages(channel: String) {
        currentChannel = channel

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            runCatching { repository.getMessages(channel) }
                .onSuccess { messages ->
                    oldestMessageId = messages.lastOrNull()?.id

                    _uiState.update {
                        it.copy(
                            messages = messages.reversed(),
                            isLoading = false,
                            hasMore = messages.size >= 20,
                            shouldScrollToBottom = true,
                            error = null,
                        )
                    }
                }.onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = it.message ?: state.error,
                        )
                    }
                }
        }
    }

    fun loadMoreMessages() {
        if (_uiState.value.isLoadingMore) return
        if (_uiState.value.hasMore.not()) return

        val channel = currentChannel ?: return
        val lastId = oldestMessageId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoadingMore = true)
            }

            runCatching {
                repository.getMessages(
                    channel = channel,
                    lastKnownId = lastId,
                )
            }.onSuccess { newMessages ->
                oldestMessageId = newMessages.lastOrNull()?.id
                _uiState.update { state ->
                    state.copy(
                        messages = newMessages.reversed() + state.messages,
                        isLoadingMore = false,
                        hasMore = newMessages.size >= 20,
                        error = null,
                    )
                }
            }.onFailure { error ->
                _uiState.update { state ->
                    state.copy(
                        isLoadingMore = false,
                        error = error.message ?: state.error,
                    )
                }
            }
        }
    }

    fun onMessageTextChange(text: String) {
        _uiState.update {
            it.copy(sendMessageText = text)
        }
    }

    fun onScrolledToBottom() {
        _uiState.update {
            it.copy(
                shouldScrollToBottom = false
            )
        }
    }

    fun sendMessage() {
        val chat = currentChat ?: return
        val text = uiState.value.sendMessageText
        if (text.isBlank()) return

        viewModelScope.launch {
            repository.sendTextMessage(
                channel = chat,
                text = text,
            )
            _uiState.update {
                it.copy(
                    sendMessageText = "",
                    shouldScrollToBottom = true,
                )
            }
            loadChat(chat)
        }
    }
}
