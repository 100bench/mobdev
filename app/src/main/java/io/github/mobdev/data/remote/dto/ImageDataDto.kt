package io.github.mobdev.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDataDto(
    @SerializedName("link")
    val link: String,
)
