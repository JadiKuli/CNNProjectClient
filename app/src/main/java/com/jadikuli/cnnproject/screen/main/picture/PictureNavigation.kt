package com.jadikuli.cnnproject.screen.main.picture

import PictureScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

fun NavGraphBuilder.pictureNavGraph(navController: NavController) {
    composable(Screen.Picture.route) {
        PictureScreen(
            onSuccess = {
                navController.navigate(Screen.Success.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            onFailed = {
                navController.navigate(Screen.Failed.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}
