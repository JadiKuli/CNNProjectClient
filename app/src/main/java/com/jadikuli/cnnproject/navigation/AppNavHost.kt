package com.jadikuli.cnnproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jadikuli.cnnproject.screen.authentication.authNavGraph
import com.jadikuli.cnnproject.screen.main.history.historyNavGraph
import com.jadikuli.cnnproject.screen.main.home.homeNavGraph
import com.jadikuli.cnnproject.screen.main.profile.profileNavGraph

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        homeNavGraph(navController)
        profileNavGraph(navController)
        historyNavGraph(navController)
        authNavGraph(navController)
    }
}