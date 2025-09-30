package com.jadikuli.cnnproject.navigation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import com.jadikuli.cnnproject.screen.MainScreen
import com.jadikuli.cnnproject.screen.authentication.AuthViewModel
import com.jadikuli.cnnproject.screen.authentication.authNavGraph

@RequiresApi(Build.VERSION_CODES.O)
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

    when (isLoggedIn) {
        null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        true -> {
            MainScreen(navController)
        }
        false -> {
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route
            ) {
                authNavGraph(navController, authViewModel)
            }
        }
    }
}

