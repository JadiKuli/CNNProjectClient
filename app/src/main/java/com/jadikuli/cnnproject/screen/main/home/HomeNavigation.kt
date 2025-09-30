package com.jadikuli.cnnproject.screen.main.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    composable(Screen.Home.route) {
        HomeScreenContent(
            onArticleClick = {
//                articleId ->
//                    navController.navigate(Screen.ArticleDetail.createRoute(articleId))
            }
        )
    }
}