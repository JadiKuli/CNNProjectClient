package com.jadikuli.cnnproject.screen.main.shared.components.card

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jadikuli.cnnproject.helper.formatDate
import com.jadikuli.cnnproject.network.model.ArticleData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleCard(
    article: ArticleData,
    onClick: () -> Unit
) {
    val imageUrl = when {
        article.id % 3 == 0 -> "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/Sport-Lacrosse2-copia-1324x773.jpg"
        article.id % 2 == 0 -> "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/people-doing-yoga-healthy-food-wellness-health-fitness-lifestyle-vector-design-generative-ai-balance-living-illustration-373826895.png"
        else -> "https://pcyjkuasuhnisovirnhn.supabase.co/storage/v1/object/public/universal/360_F_729079170_kgesCf8chbpt4EVYOM0wEnDmoJ6uBb6Y.jpg"
    }

    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(175.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Article Image",
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            Modifier
                .height(75.dp)
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = formatDate(article.createdAt),
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Right,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}