package com.jadikuli.cnnproject.screen.main.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jadikuli.cnnproject.navigation.Screen
import com.jadikuli.cnnproject.screen.main.article.ArticleScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    // Home Screen
    composable(Screen.Home.route) {
        HomeScreenContent(
            onArticleClick = { articleId ->
                navController.navigate(Screen.DetailArticle.createRoute(articleId))
            }
        )
    }

    // Article Screen
    composable(
        route = "${Screen.DetailArticle.route}/{articleId}",
        arguments = listOf(navArgument("articleId") { type = NavType.IntType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt("articleId") ?: 0
        ArticleScreen(articleId = id)
    }
}