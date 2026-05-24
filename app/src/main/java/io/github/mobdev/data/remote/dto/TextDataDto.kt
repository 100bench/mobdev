package io.github.mobdev.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TextDataDto(
    @SerializedName("text")
    val text: String,
)
