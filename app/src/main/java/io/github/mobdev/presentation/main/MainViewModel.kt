package io.github.mobdev.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.mobdev.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _selectedChat = MutableStateFlow<String?>(null)

    val selectedChat = _selectedChat.asStateFlow()

    fun openChat(chat: String) {
        _selectedChat.value = chat
    }

    fun closeChat() {
        _selectedChat.value = null
    }

    fun logout(
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch {
            authRepository.logout()
            onSuccess()
        }
    }
}
