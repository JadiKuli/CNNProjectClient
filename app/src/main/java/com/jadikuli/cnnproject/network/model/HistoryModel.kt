package com.jadikuli.cnnproject.network.model

import com.google.gson.annotations.SerializedName

data class HistoryData(
    val id: Int,
    val indication: String,
    @SerializedName("created_at") val createdAt: String,
)
typealias HistoryResponse = BaseResponse<List<HistoryData>>
typealias HistoryLatestResponse = BaseResponse<HistoryData>