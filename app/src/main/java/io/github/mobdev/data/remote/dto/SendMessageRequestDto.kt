package io.github.mobdev.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SendMessageRequestDto(
    @SerializedName("from")
    val from: String,

    @SerializedName("to")
    val to: String,

    @SerializedName("data")
    val data: MessageDataDto,
)
