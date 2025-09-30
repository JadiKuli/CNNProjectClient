package com.jadikuli.cnnproject.screen.main.profile

import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.MainActivity
import com.jadikuli.cnnproject.navigation.Screen
import com.jadikuli.cnnproject.screen.authentication.AuthViewModel

fun NavGraphBuilder.profileNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    composable(Screen.Profile.route) {
        ProfileScreenContent(
            onLogout = {
                authViewModel.logout()
            }
        )
    }
}