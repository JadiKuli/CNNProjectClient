package com.jadikuli.cnnproject.screen.main.profile

import android.net.Uri
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jadikuli.cnnproject.R
import com.jadikuli.cnnproject.screen.authentication.TextGrayColor
import com.jadikuli.cnnproject.screen.authentication.components.CustomTextField

@Composable
fun EditProfileScreenContent(
    viewModel: ProfileViewModel = hiltViewModel(),
    onSubmit: () -> Unit,
    onGalleryPick: (Uri) -> Unit
) {
    val profile by viewModel.profile.collectAsState()

    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is UploadUiStateProfile.Success) {
            viewModel.fetchProfile()
            viewModel.resetState()
        }
    }

    LaunchedEffect(profile) {
        fullname = profile?.name ?: ""
        email = profile?.email ?: ""
        phoneNumber = profile?.phoneNumber ?: ""
    }

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) {
            uri: Uri? ->
            uri?.let {
                viewModel.uploadProfileImage(context, uri)
            }
        }

        profile.let {
            if (it != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AsyncImage(
                        model = if (!it.profilePhoto.isNullOrEmpty()) "https://cnnproject.kuncipintu.my.id/storage/" + it.profilePhoto
                        else "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/vector-flat-illustration-gray-color-avatar-user-profile-person-icon-profile-picture-suitable-social-media-profiles-icons-screensavers-as-templatex9xa_719432-1056.jpg",
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                launcher.launch( "image/*" )
                            }
                    )

                    Text(
                        text = "Tap to change Profile Image",
                        fontSize = 12.sp,
                        color = TextGrayColor,
                        letterSpacing = 1.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Full Name",
                fontSize = 12.sp,
                color = TextGrayColor,
                letterSpacing = 1.sp
            )
            CustomTextField(
                value = fullname,
                onValueChange = { fullname = it },
                icon = Icons.Default.AccountCircle
            )
        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Email",
                fontSize = 12.sp,
                color = TextGrayColor,
                letterSpacing = 1.sp
            )
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )
        }


        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Phone Number",
                fontSize = 12.sp,
                color = TextGrayColor,
                letterSpacing = 1.sp
            )
            CustomTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                icon = Icons.Default.Phone,
                keyboardType = KeyboardType.Email
            )
        }

        Button(
            onClick = {
                viewModel.updateProfile(fullname, email, phoneNumber)
                onSubmit()
            },
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.main_color),
                contentColor = Color.White
            ),
        ) {
            Text(
                "Simpan Perubahan",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}