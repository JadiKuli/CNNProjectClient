package com.jadikuli.cnnproject.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    composable(Screen.Profile.route) {
        ProfileScreenContent()
    }
}