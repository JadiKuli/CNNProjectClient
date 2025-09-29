package com.jadikuli.cnnproject.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    composable(Screen.Home.route) {
        HomeScreenContent()
    }
}