package io.github.mobdev.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("pwd")
    val password: String,
)
