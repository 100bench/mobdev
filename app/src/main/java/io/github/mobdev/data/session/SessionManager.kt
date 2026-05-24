package io.github.mobdev.data.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val storage: SessionStorage,
) {
    private val _isAuthorized = MutableStateFlow(
        storage.getToken() != null
    )

    val isAuthorized = _isAuthorized.asStateFlow()

    var token: String? = storage.getToken()
        private set

    var username: String? = storage.getUsername()
        private set

    private var password: String? = storage.getPassword()

    fun saveAuth(
        username: String,
        password: String,
        token: String,
    ) {
        this.username = username
        this.password = password
        this.token = token

        storage.saveSession(
            username = username,
            password = password,
            token = token,
        )
        _isAuthorized.value = true
    }

    fun clear() {
        token = null
        username = null
        password = null

        storage.clear()

        _isAuthorized.value = false
    }
}
