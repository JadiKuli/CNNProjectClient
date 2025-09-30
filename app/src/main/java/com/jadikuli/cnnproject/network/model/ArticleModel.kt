package com.jadikuli.cnnproject.network.model

import com.google.gson.annotations.SerializedName

data class ArticleData(
    val id: Int,
    val title: String,
    @SerializedName("created_at") val createdAt: String,
)
typealias ArticleResponse = BaseResponse<List<ArticleData>>