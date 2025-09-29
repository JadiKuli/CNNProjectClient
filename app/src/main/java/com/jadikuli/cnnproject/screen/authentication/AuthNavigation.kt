package com.jadikuli.cnnproject.screen.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    composable(Screen.Login.route) {
        LoginScreen()
    }

    composable(Screen.Register.route) {
        RegisterScreen()
    }
}