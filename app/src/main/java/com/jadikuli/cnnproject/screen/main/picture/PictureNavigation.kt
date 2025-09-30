package com.jadikuli.cnnproject.screen.main.picture

import PictureScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

fun NavGraphBuilder.pictureNavGraph(navController: NavController) {
    composable(Screen.Picture.route) {
        PictureScreen()
    }
}
