package com.jadikuli.cnnproject.screen.main.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen
import com.jadikuli.cnnproject.screen.main.history.alert.FailedScreen
import com.jadikuli.cnnproject.screen.main.history.alert.SuccessScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.historyNavGraph(navController: NavController) {
    composable(Screen.History.route) {
        HistoryScreenContent()
    }

    composable(Screen.Success.route) {
        SuccessScreen(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
        )
    }

    composable(Screen.Failed.route) {
        FailedScreen(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}