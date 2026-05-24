package io.github.mobdev.presentation.navigation

import android.net.Uri

sealed class AppScreen(val route: String) {

    data object Login : AppScreen("login")

    data object Main : AppScreen("main")

    data object Image : AppScreen("image/{path}") {
        fun createRoute(path: String): String {
            return "image/${
                Uri.encode(path)
            }"
        }
    }
}
