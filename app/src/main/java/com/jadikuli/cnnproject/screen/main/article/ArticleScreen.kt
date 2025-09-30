package com.jadikuli.cnnproject.screen.main.article

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jadikuli.cnnproject.helper.formatDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleScreen(
    articleId: Int,
    viewModel: ArticleViewModel = hiltViewModel()
) {
    val article by viewModel.article.collectAsState()
    LaunchedEffect(articleId) { viewModel.fetchArticle(articleId) }

    article?.let { data ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val imageUrl = getArticleImage(data.id.toInt())
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = data.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date
            Text(
                text = formatDate(data.createdAt),
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content
            Text(
                text = data.content,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    } ?: run {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

fun getArticleImage(id: Int): String {
    return when {
        id % 3 == 1 -> "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/360_F_729079170_kgesCf8chbpt4EVYOM0wEnDmoJ6uBb6Y.jpg"
        id % 3 == 2 -> "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/people-doing-yoga-healthy-food-wellness-health-fitness-lifestyle-vector-design-generative-ai-balance-living-illustration-373826895.png"
        else -> "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/Sport-Lacrosse2-copia-1324x773.jpg"
    }
}
