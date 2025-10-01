package com.jadikuli.cnnproject.repository

import com.jadikuli.cnnproject.network.ApiService
import com.jadikuli.cnnproject.network.model.HistoryData
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getHistory(): List<HistoryData>? {
        return try {
            val response = api.getHistory()
            if (response.status == "success") response.data else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getLatestHistory(): HistoryData? {
        return try {
            val response = api.getLatestHistory()
            if (response.status == "success") response.data else null
        } catch (e: Exception) {
            null
        }
    }
}