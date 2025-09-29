package com.jadikuli.cnnproject.screen.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    composable(Screen.Login.route) {
        LoginScreen(
            onLogin = { email, password ->
                authViewModel.login(email, password)
            },
            onNavigateToRegister = {
                navController.navigate("register")
            }
        )
    }

    composable(Screen.Register.route) {
        RegisterScreen()
    }
}