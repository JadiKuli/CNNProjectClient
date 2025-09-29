package com.jadikuli.cnnproject.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jadikuli.cnnproject.screen.authentication.AuthViewModel
import com.jadikuli.cnnproject.screen.authentication.authNavGraph
import com.jadikuli.cnnproject.screen.main.history.historyNavGraph
import com.jadikuli.cnnproject.screen.main.home.homeNavGraph
import com.jadikuli.cnnproject.screen.main.profile.profileNavGraph

@Composable
fun AppNavHost(authViewModel: AuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        authViewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    if (isLoggedIn == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn == true) "home" else "login"
        ) {
            authNavGraph(navController, authViewModel)
            homeNavGraph(navController)
            historyNavGraph(navController)
            profileNavGraph(navController)
        }
    }
}
