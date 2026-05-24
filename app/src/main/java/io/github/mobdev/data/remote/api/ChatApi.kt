package io.github.mobdev.data.remote.api

import io.github.mobdev.data.remote.dto.MessageDto
import io.github.mobdev.data.remote.dto.SendMessageRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApi {
    @GET("channels")
    suspend fun getChannels(): List<String>

    @GET("channel/{channel}")
    suspend fun getMessages(
        @Path("channel")
        channel: String,

        @Query("limit")
        limit: Int = 20,

        @Query("lastKnownId")
        lastKnownId: String? = null,

        @Query("reverse")
        reverse: Boolean = true,
    ): List<MessageDto>

    @POST("messages")
    suspend fun sendMessage(
        @Body message: SendMessageRequestDto,
    ): String
}
