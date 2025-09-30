package com.jadikuli.cnnproject.repository

import com.jadikuli.cnnproject.network.ApiService
import com.jadikuli.cnnproject.network.model.ArticleData
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getArticles(): List<ArticleData>? {
        return try {
            val response = api.articles()
            if (response.status == "success") response.data else null
        } catch (e: Exception) {
            null
        }
    }
}
