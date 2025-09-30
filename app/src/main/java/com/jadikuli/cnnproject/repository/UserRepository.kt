package com.jadikuli.cnnproject.repository

import com.jadikuli.cnnproject.network.ApiService
import com.jadikuli.cnnproject.network.model.ProfileData
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getProfile(): ProfileData? {
        return try {
            val response = api.profile()
            if (response.status == "success") response.data else null
        } catch (e: Exception) {
            null
        }
    }
}
