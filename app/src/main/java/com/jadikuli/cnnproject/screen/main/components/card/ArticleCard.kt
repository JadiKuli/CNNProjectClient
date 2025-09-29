package com.jadikuli.cnnproject.screen.main.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArticleCard() {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(175.dp)
    ) {
        Box(
            Modifier
                .background(Color.Gray)
                .height(100.dp)
                .fillMaxWidth()
        ) {}

        Column(
            Modifier
                .height(75.dp)
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Lorem ipsum dolor sit amet",
                fontWeight = FontWeight.SemiBold
            )

            Text(
                "28 Juni 2024",
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Right,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}