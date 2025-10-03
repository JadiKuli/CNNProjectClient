package com.jadikuli.cnnproject.screen.main.history.alert

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jadikuli.cnnproject.R
import com.jadikuli.cnnproject.helper.formatDate
import com.jadikuli.cnnproject.screen.main.history.HistoryViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SuccessScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchLatestHistory()
    }

    val latestHistory by viewModel.latestHistory.collectAsState()
    val indicationColor = when (latestHistory?.indication) {
        "Diabetes" -> Color(0xFFB22222)
        "Normal" -> Color(0xFF347433)
        "CF" -> Color(0xFFFF6F3C)
        "CFRD" -> colorResource(R.color.main_color)
        else -> Color.Black
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.check_circle),
            contentDescription = null,
            tint = indicationColor,
            modifier = Modifier.size(125.dp)
        )

        Text(
            "Data Berhasil Terdeteksi",
            modifier = Modifier
                .padding(top = 16.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Text(
            "Kondisi Anda Saat Ini",
            modifier = Modifier
                .padding(top = 24.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        latestHistory?.let {
            val indicationText = when (latestHistory?.indication) {
                "Diabetes" -> Text(" (1 mM glukosa dan 20 mmol/L Cl-)")
                "Normal" -> Text(" (0.05 mM glukosa dan 15 mmol/L Cl-)")
                "CF" -> Text(" (0.05 mM glukosa dan 15 mmol/L Cl-)")
                "CFRD" -> Text(" (0.05 mM glukosa dan 75 mmol/L Cl-)")
                else -> Text("")
            }

            Text(
                it.indication,
                modifier = Modifier
                    .padding(top = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        Text(
            "Waktu Pengambilan",
            modifier = Modifier
                .padding(top = 16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        latestHistory?.let {
            Text(
                formatDate(latestHistory!!.createdAt),
                modifier = Modifier
                    .padding(top = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick,
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.LightGray
            )
        ) {
            Text(
                "Kembali ke Beranda"
            )
        }
    }
}