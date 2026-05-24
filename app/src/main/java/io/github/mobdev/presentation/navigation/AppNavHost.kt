package io.github.mobdev.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.mobdev.data.session.SessionManager
import io.github.mobdev.presentation.chats.ChatsViewModel
import io.github.mobdev.presentation.image.ImageScreen
import io.github.mobdev.presentation.login.LoginScreen
import io.github.mobdev.presentation.main.MainScreen
import io.github.mobdev.presentation.main.MainViewModel
import io.github.mobdev.presentation.messages.MessagesViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    sessionManager: SessionManager,
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val chatsViewModel: ChatsViewModel = hiltViewModel()
    val messagesViewModel: MessagesViewModel = hiltViewModel()

    val isAuthorized by sessionManager.isAuthorized.collectAsState()

    val startDestination = if (isAuthorized) {
        AppScreen.Main.route
    } else {
        AppScreen.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(AppScreen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppScreen.Main.route) {
                        popUpTo(AppScreen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppScreen.Main.route) {
            MainScreen(
                mainViewModel = mainViewModel,
                chatsViewModel = chatsViewModel,
                messagesViewModel = messagesViewModel,
                onOpenImage = { path ->
                    navController.navigate(
                        AppScreen.Image.createRoute(path)
                    )
                },
                onLogout = {
                    mainViewModel.logout {
                        navController.navigate(AppScreen.Login.route) {
                            popUpTo(
                                AppScreen.Main.route
                            ) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        composable(
            route = AppScreen.Image.route,
            arguments = listOf(
                navArgument("path") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val path = backStackEntry.arguments
                ?.getString("path")
                ?.let(Uri::decode)
                .orEmpty()

            ImageScreen(
                imagePath = path,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}
