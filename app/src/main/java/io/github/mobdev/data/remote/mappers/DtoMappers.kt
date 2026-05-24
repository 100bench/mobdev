package io.github.mobdev.data.remote.mappers

import io.github.mobdev.data.remote.dto.MessageDto
import io.github.mobdev.domain.model.Message
import io.github.mobdev.domain.model.MessageContent

fun MessageDto.toDomain(): Message {
    val content = when {
        data.text != null -> MessageContent.Text(
            text = data.text.text,
        )

        data.image != null -> MessageContent.Image(
            imagePath = data.image.link,
        )

        else -> throw IllegalStateException("Unknown message type")
    }

    return Message(
        id = id,
        from = from,
        to = to,
        content = content,
        timestamp = time,
    )
}
