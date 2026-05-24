package io.github.mobdev.data.session

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionStorage @Inject constructor(
    @ApplicationContext
    context: Context,
) {
    private val prefs = context.getSharedPreferences(
        SESSION_KEY,
        Context.MODE_PRIVATE
    )

    fun saveSession(
        username: String,
        password: String,
        token: String,
    ) {
        prefs.edit {
            putString(USERNAME_KEY, username)
            putString(PASSWORD_KEY, password)
            putString(TOKEN_KEY, token)
        }
    }

    fun getUsername(): String? {
        return prefs.getString(USERNAME_KEY, null)
    }

    fun getPassword(): String? {
        return prefs.getString(PASSWORD_KEY, null)
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    fun clear() {
        prefs.edit {
            clear()
        }
    }

    private companion object {
        private const val SESSION_KEY = "session"
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
        private const val TOKEN_KEY = "token"
    }
}
