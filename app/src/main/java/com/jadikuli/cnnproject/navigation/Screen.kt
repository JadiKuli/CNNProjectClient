package com.jadikuli.cnnproject.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object History : Screen("history")
    object Profile : Screen("profile")
    object Register : Screen("register")
    object Login : Screen("login")
}