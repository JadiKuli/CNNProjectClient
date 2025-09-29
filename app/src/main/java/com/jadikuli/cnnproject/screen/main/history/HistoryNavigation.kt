package com.jadikuli.cnnproject.screen.main.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

fun NavGraphBuilder.historyNavGraph(navController: NavController) {
    composable(Screen.History.route) {
        HistoryScreenContent()
    }
}