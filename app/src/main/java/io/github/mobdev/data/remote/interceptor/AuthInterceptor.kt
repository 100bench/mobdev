package io.github.mobdev.data.remote.interceptor

import io.github.mobdev.data.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .apply {
                sessionManager.token?.let {
                    addHeader("X-Auth-Token", it)
                }
            }
            .build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            sessionManager.clear()
        }
        return response
    }
}
