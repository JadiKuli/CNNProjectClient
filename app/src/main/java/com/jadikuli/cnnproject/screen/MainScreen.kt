package com.jadikuli.cnnproject.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jadikuli.cnnproject.R
import com.jadikuli.cnnproject.navigation.Screen
import com.jadikuli.cnnproject.screen.authentication.AuthViewModel
import com.jadikuli.cnnproject.screen.main.history.historyNavGraph
import com.jadikuli.cnnproject.screen.main.home.homeNavGraph
import com.jadikuli.cnnproject.screen.main.picture.pictureNavGraph
import com.jadikuli.cnnproject.screen.main.profile.ProfileViewModel
import com.jadikuli.cnnproject.screen.main.profile.profileNavGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val items = listOf(
        Screen.Home,
        Screen.Picture,
        Screen.History,
        Screen.Profile,
    )

    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 16.dp)
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(50))
                    .shadow(10.dp, RoundedCornerShape(50))
                    .background(Color.White)
                    .padding(vertical = 8.dp, horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEach { screen ->
                        val currentDestination =
                            navController.currentBackStackEntryAsState().value?.destination
                        val selected = currentDestination?.route == screen.route

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                                .clickable {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            screen.iconRes?.let { resId ->
                                Icon(
                                    painter = painterResource(id = resId),
                                    contentDescription = null,
                                    modifier = Modifier.size(28.dp),
                                    tint = if (selected) colorResource(R.color.main_color) else Color.Black
                                )
                            }

                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = screen.title ?: "",
                                color = if (selected) colorResource(R.color.main_color) else Color.Black,
                                fontSize = 12.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                            )
                        }

                    }
                }
            }
        }
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.fillMaxSize()
        ) {
            homeNavGraph(navController)
            historyNavGraph(navController)
            profileNavGraph(navController, authViewModel, profileViewModel)
            pictureNavGraph(navController)
        }
    }
}

