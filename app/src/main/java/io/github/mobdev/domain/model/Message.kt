package io.github.mobdev.domain.model

data class Message(
    val id: String,
    val from: String,
    val to: String,
    val content: MessageContent,
    val timestamp: Long,
)
