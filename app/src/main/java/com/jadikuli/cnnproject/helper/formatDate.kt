package com.jadikuli.cnnproject.helper

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateString: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("id"))
        val dateTime = LocalDateTime.parse(dateString, inputFormatter)
        dateTime.format(outputFormatter)
    } catch (e: Exception) {
        dateString
    }
}