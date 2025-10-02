package com.jadikuli.cnnproject.screen.main.profile

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jadikuli.cnnproject.navigation.Screen
import com.jadikuli.cnnproject.screen.authentication.AuthViewModel

fun NavGraphBuilder.profileNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel
) {
    composable(Screen.Profile.route) {
        ProfileScreenContent(
            onLogout = {
                authViewModel.logout()
            },
            onNavigateToEditProfile = {
                navController.navigate(Screen.UpdateProfile.route)
            }
        )
    }

    composable(Screen.UpdateProfile.route) {
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            profileViewModel.toastEventProfile.collect { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }

        EditProfileScreenContent(
            onGalleryPick = { uri ->
//                viewModel.uploadImage(context, uri)
            },
            onSubmit = {
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.Profile.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}