package io.github.mobdev.presentation.chats

data class ChatsUiState(
    val isLoading: Boolean = false,
    val channels: List<String> = emptyList(),
    val error: String? = null,
)
