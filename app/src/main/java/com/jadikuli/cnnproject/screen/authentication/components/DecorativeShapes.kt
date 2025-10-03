package com.jadikuli.cnnproject.screen.authentication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jadikuli.cnnproject.R
import com.jadikuli.cnnproject.screen.authentication.LightOrangeColor
import com.jadikuli.cnnproject.screen.authentication.OrangeColor

@Composable
fun DecorativeShapes() {
    // Top right shape
    Box(
        modifier = Modifier
            .offset(x = 200.dp, y = (-50).dp)
            .size(200.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(colorResource(R.color.main_color).copy(alpha = 0.3f))
    )

    // Bottom left shape
    Box(
        modifier = Modifier
            .offset(x = (-50).dp, y = 600.dp)
            .size(250.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(colorResource(R.color.main_color).copy(alpha = 0.5f))
    )
}