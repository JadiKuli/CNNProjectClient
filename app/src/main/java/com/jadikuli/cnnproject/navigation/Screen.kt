package com.jadikuli.cnnproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String? = null, val icon: ImageVector? = null) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object History : Screen("history", "History", Icons.Default.Home)
    object Profile : Screen("profile", "Profile", Icons.Default.Home)
    object Register : Screen("register")
    object Login : Screen("login")
}