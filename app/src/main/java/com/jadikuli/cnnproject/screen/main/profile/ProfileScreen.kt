package com.jadikuli.cnnproject.screen.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jadikuli.cnnproject.screen.authentication.AuthViewModel

@Composable
fun ProfileScreenContent(
    viewModel: ProfileViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val profile by viewModel.profile.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                profile?.let {
                    AsyncImage(
                        model = it.profilePhoto ?: "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/vector-flat-illustration-gray-color-avatar-user-profile-person-icon-profile-picture-suitable-social-media-profiles-icons-screensavers-as-templatex9xa_719432-1056.jpg",
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        it.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        it.email,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        it.phoneNumber ?: "null",
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        Text(
            "Pengaturan dan Preferensi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                Modifier
                    .padding(
                        vertical = 20.dp,
                        horizontal = 15.dp
                    )
            ) {
                Text(
                    "Edit Profile",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                Modifier
                    .padding(
                        vertical = 20.dp,
                        horizontal = 15.dp
                    )
            ) {
                Text(
                    "Panduan Aplikasi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Row(
                Modifier
                    .padding(
                        vertical = 20.dp,
                        horizontal = 15.dp
                    )
            ) {
                Text(
                    "Tentang Aplikasi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(100))
                .background(Color.Red)
                .clickable {
                    authViewModel.logout()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Keluar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}