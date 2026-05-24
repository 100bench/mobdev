package io.github.mobdev.presentation.messages

import io.github.mobdev.domain.model.Message

data class MessagesUiState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMore: Boolean = true,
    val shouldScrollToBottom: Boolean = false,
    val isSending: Boolean = false,
    val sendMessageText: String = "",
    val messages: List<Message> = emptyList(),
    val error: String? = null,
)
