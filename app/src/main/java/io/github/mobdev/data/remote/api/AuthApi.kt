package io.github.mobdev.data.remote.api

import io.github.mobdev.data.remote.dto.LoginRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequestDto,
    ): String

    @POST("logout")
    suspend fun logout(): Response<Unit>
}
