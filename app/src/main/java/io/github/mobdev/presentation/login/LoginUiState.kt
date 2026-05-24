package io.github.mobdev.presentation.login

import androidx.annotation.StringRes

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    @StringRes
    val errorMessageResId: Int? = null,
)
