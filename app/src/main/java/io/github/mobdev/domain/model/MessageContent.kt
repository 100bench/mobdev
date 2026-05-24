package io.github.mobdev.domain.model

sealed interface MessageContent {
    data class Text(val text: String) : MessageContent
    data class Image(val imagePath: String): MessageContent
}
