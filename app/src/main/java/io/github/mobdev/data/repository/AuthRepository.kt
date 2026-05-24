package io.github.mobdev.data.repository

import io.github.mobdev.data.remote.api.AuthApi
import io.github.mobdev.data.remote.dto.LoginRequestDto
import io.github.mobdev.data.session.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val sessionManager: SessionManager,
) {
    suspend fun login(
        username: String,
        password: String,
    ): Result<Unit> {
        return runCatching {
            val token = api.login(
                LoginRequestDto(
                    name = username,
                    password = password,
                )
            )
            sessionManager.saveAuth(
                username = username,
                password = password,
                token = token,
            )
        }
    }

    suspend fun logout() {
        runCatching {
            api.logout()
        }
        sessionManager.clear()
    }
}
