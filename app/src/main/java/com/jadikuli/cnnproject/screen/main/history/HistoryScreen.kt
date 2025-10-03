package com.jadikuli.cnnproject.screen.main.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jadikuli.cnnproject.screen.main.shared.components.card.HistoryCard

//@Preview(showBackground = true)
//@Composable
//fun HistoryScreenPreview() {
//    MaterialTheme {
//        HistoryScreenContent()
//    }
//}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreenContent(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val history by viewModel.history.collectAsState()
    val latestHistory by viewModel.latestHistory.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchHistory()
        viewModel.fetchLatestHistory()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ) {
        Text(
            "Pemeriksaan Terakhir",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(Modifier.height(10.dp))

        latestHistory?.let {
            HistoryCard(it)
        } ?: Text(
            text = "Empty Data",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Spacer(Modifier.height(20.dp))

        Text(
            "Riwayat Pemeriksaan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        if (history.filterNotNull().isEmpty()) {
            Text(
                text = "Empty Data",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 10.dp)
            )
        } else {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(history.filterNotNull()) { historyData ->
                    HistoryCard(historyData)
                }

                item {
                    Spacer(Modifier.height(85.dp))
                }
            }
        }
    }
}