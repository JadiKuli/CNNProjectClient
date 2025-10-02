package com.jadikuli.cnnproject.repository

import com.jadikuli.cnnproject.network.ApiService
import com.jadikuli.cnnproject.network.model.ProfileData
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
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

    suspend fun uploadImage(file: File) {
        val requestBody = file.asRequestBody("image/jpeg".toMediaType())
        val part = MultipartBody.Part.createFormData("image", file.name, requestBody)
        api.uploadImage(part)
    }
}
