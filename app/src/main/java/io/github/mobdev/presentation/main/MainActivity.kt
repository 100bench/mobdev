package io.github.mobdev.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import io.github.mobdev.data.session.SessionManager
import io.github.mobdev.presentation.navigation.AppNavHost
import io.github.mobdev.presentation.theme.ChatsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChatsTheme {
                val navController = rememberNavController()

                AppNavHost(
                    navController = navController,
                    sessionManager = sessionManager,
                )
            }
        }
    }
}
