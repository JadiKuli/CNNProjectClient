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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jadikuli.cnnproject.R
import com.jadikuli.cnnproject.screen.authentication.components.CustomTextField
import com.jadikuli.cnnproject.screen.authentication.components.DecorativeShapes

val OrangeColor = Color(0xFFFDB45C)
val LightOrangeColor = Color(0xFFFDC98E)
val BackgroundColor = Color(0xFFF5F5F5)
val TextGrayColor = Color(0xFF9E9E9E)

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    onNavigateToRegister: () -> Unit

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                        text = "Login",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Please sign in to continue.",
                        fontSize = 14.sp,
                        color = TextGrayColor
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Email Field
                    Text(
                        text = "EMAIL",
                        fontSize = 10.sp,
                        color = TextGrayColor,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        icon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Password Field
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PASSWORD",
                            fontSize = 10.sp,
                            color = TextGrayColor,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        icon = Icons.Default.Lock,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Login Button
                    Button(
                        onClick = { onLogin(email, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.main_color)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(
                            text = "LOGIN",
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

            // Sign up link
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 14.sp,
                    color = TextGrayColor
                )
                Text(
                    text = "Sign up",
                    fontSize = 14.sp,
                    color = colorResource(R.color.main_color),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { onNavigateToRegister() }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    MaterialTheme {
//        LoginScreen()
//    }
//}
