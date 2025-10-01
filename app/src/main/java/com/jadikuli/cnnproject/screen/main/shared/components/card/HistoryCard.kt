package com.jadikuli.cnnproject.screen.main.shared.components.card

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jadikuli.cnnproject.helper.formatDate
import com.jadikuli.cnnproject.network.model.HistoryData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryCard(
    history: HistoryData
) {
    val indicationColor = when (history.indication) {
        "Diabetes" -> Color(0xFFB22222)
        "Normal" -> Color(0xFF347433)
        "CF" -> Color(0xFFFF6F3C)
        "CFRD" -> Color(0xFFFFC107)
        else -> Color.Black
    }

    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(indicationColor)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                "Tanggal Pemeriksaan:",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                formatDate(history.createdAt),
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            history.indication,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}