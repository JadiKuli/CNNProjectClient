package com.jadikuli.cnnproject.screen.authentication

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jadikuli.cnnproject.R
import com.jadikuli.cnnproject.screen.authentication.components.CustomTextField
import com.jadikuli.cnnproject.screen.authentication.components.DecorativeShapes

@Composable
fun RegisterScreen(
    onRegister: (String, String, String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Decorative shapes
        DecorativeShapes()

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Create Account",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Full Name Field
                    Text(
                        text = "FULL NAME",
                        fontSize = 10.sp,
                        color = TextGrayColor,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = name,
                        onValueChange = { name = it },
                        icon = Icons.Default.Person
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Email Field
                    Text(
                        text = "EMAIL",
                        fontSize = 10.sp,
                        color = TextGrayColor,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        icon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Password Field
                    Text(
                        text = "PASSWORD",
                        fontSize = 10.sp,
                        color = TextGrayColor,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        icon = Icons.Default.Lock,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Confirm Password Field
                    Text(
                        text = "CONFIRM PASSWORD",
                        fontSize = 10.sp,
                        color = TextGrayColor,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        icon = Icons.Default.Lock,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Sign Up Button
                    Button(
                        onClick = {
                            onRegister(name, email, password)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.main_color)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(
                            text = "SIGN UP",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sign in link
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have a account? ",
                    fontSize = 14.sp,
                    color = TextGrayColor
                )
                Text(
                    text = "Sign in",
                    fontSize = 14.sp,
                    color = colorResource(R.color.main_color),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        onNavigateToLogin()
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//    RegisterScreen()
//}