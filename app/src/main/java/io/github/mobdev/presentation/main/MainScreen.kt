package io.github.mobdev.presentation.main

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import io.github.mobdev.presentation.chats.ChatsScreen
import io.github.mobdev.presentation.chats.ChatsViewModel
import io.github.mobdev.presentation.chats.EmptyChatPlaceholder
import io.github.mobdev.presentation.messages.MessagesScreen
import io.github.mobdev.presentation.messages.MessagesViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    chatsViewModel: ChatsViewModel,
    messagesViewModel: MessagesViewModel,
    onOpenImage: (String) -> Unit,
    onLogout: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val selectedChat by mainViewModel.selectedChat.collectAsState()

    BackHandler(enabled = selectedChat != null) {
        mainViewModel.closeChat()
    }

    if (isLandscape) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            ChatsScreen(
                modifier = Modifier.weight(1f),
                viewModel = chatsViewModel,
                selectedChat = selectedChat,
                onChatClick = { chat ->
                    mainViewModel.openChat(chat)
                    messagesViewModel.loadChat(chat)
                },
                onLogout = onLogout,
            )

            Box(
                modifier = Modifier.weight(2f)
            ) {
                if (selectedChat == null) {
                    EmptyChatPlaceholder()
                } else {
                    MessagesScreen(
                        viewModel = messagesViewModel,
                        onBack = {
                            mainViewModel.closeChat()
                        },
                        onOpenImage = onOpenImage,
                    )
                }
            }
        }
    } else {
        if (selectedChat == null) {
            ChatsScreen(
                viewModel = chatsViewModel,
                onChatClick = { chat ->
                    mainViewModel.openChat(chat)
                    messagesViewModel.loadChat(chat)
                },
                onLogout = onLogout,
            )
        } else {
            MessagesScreen(
                viewModel = messagesViewModel,
                onBack = {
                    mainViewModel.closeChat()
                },
                onOpenImage = onOpenImage,
            )
        }
    }
}
