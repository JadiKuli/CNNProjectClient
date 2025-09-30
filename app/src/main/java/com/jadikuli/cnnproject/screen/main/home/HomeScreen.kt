package com.jadikuli.cnnproject.screen.main.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jadikuli.cnnproject.screen.main.shared.components.card.ArticleCard

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreenContent(
            onArticleClick = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onArticleClick: (Int) -> Unit
) {
    val articles by viewModel.articles.collectAsState()
    val profile by viewModel.profile.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .padding(bottom = 76.dp),
        contentPadding = PaddingValues(bottom = 22.dp)
    ) {
        item {
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
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        "Selamat Datang",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black
                    )

                    Spacer(Modifier.height(5.dp))

                    profile?.let {
                        Text(
                            it.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(Modifier.height(15.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 0.5.dp,
                        color = Color.Gray
                    )

                    Spacer(Modifier.height(15.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Pemeriksaan Terakhir",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.Black
                        )

                        Text(
                            "Sehat",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Green
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            Text(
                "Rekomendasi Artikel",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        item { Spacer(Modifier.height(20.dp)) }

        items(articles) { article ->
            ArticleCard(
                article = article,
                onClick = { onArticleClick(article.id) }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

