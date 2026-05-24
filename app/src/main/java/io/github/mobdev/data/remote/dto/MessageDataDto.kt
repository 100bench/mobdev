package io.github.mobdev.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MessageDataDto(
    @SerializedName("Text")
    val text: TextDataDto? = null,

    @SerializedName("Image")
    val image: ImageDataDto? = null,
)
