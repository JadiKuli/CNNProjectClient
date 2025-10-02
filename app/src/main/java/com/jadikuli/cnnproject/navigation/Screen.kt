package com.jadikuli.cnnproject.navigation

import androidx.annotation.DrawableRes
import com.jadikuli.cnnproject.R

sealed class Screen(
    val route: String,
    val title: String? = null,
    @DrawableRes val iconRes: Int? = null
) {
    object Home : Screen("home", "Home", R.drawable.home)
    object History : Screen("history", "History", R.drawable.history)
    object Profile : Screen("profile", "Profile", R.drawable.account)
    object Register : Screen("register")
    object Login : Screen("login")
    object DetailArticle : Screen("article_detail") {
        fun createRoute(articleId: Int) = "$route/$articleId"
    }

    object Success : Screen("success")
    object Failed : Screen("failed")

    object Picture : Screen("picture", "Upload", R.drawable.camera)
    object UpdateProfile : Screen("update_profile")
}
