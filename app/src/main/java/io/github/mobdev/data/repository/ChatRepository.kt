package io.github.mobdev.data.repository

import io.github.mobdev.data.remote.api.ChatApi
import io.github.mobdev.data.remote.dto.MessageDataDto
import io.github.mobdev.data.remote.dto.SendMessageRequestDto
import io.github.mobdev.data.remote.dto.TextDataDto
import io.github.mobdev.data.remote.mappers.toDomain
import io.github.mobdev.data.session.SessionManager
import io.github.mobdev.domain.model.Message
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val api: ChatApi,
    private val sessionManager: SessionManager,
) {
    suspend fun getChannels(): List<String> {
        return api.getChannels()
    }

    suspend fun getMessages(
        channel: String,
        limit: Int = 20,
        lastKnownId: String? = null,
        reverse: Boolean = true,
    ): List<Message> {
        return api.getMessages(
            channel = channel,
            limit = limit,
            lastKnownId = lastKnownId,
            reverse = reverse,
        ).map {
            it.toDomain()
        }
    }

    suspend fun sendTextMessage(
        channel: String,
        text: String,
    ) {
        val username = sessionManager.username ?: error("No username")

        api.sendMessage(
            SendMessageRequestDto(
                from = username,
                to = channel,
                data = MessageDataDto(
                    text = TextDataDto(text),
                ),
            )
        )
    }
}
