package io.github.mobdev.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mobdev.R
import io.github.mobdev.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState = _uiState.asStateFlow()

    fun updateUsername(value: String) {
        _uiState.update {
            it.copy(username = value)
        }
    }

    fun updatePassword(value: String) {
        _uiState.update {
            it.copy(password = value)
        }
    }

    fun login(onSuccess: () -> Unit) {
        val state = _uiState.value

        if (state.username.isBlank() || state.password.isBlank())
            return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessageResId = null
                )
            }
            val result = repository.login(
                username = state.username,
                password = state.password
            )

            result
                .onSuccess {
                    _uiState.update {
                        it.copy(isLoading = false)
                    }
                    onSuccess()
                }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageResId = R.string.wrong_login_or_password,
                        )
                    }
                }
        }
    }

    fun clearError() {
        _uiState.update {
            it.copy(errorMessageResId = null)
        }
    }
}
