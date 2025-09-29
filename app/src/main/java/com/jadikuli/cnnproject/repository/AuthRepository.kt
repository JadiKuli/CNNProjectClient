package com.jadikuli.cnnproject.repository

import com.jadikuli.cnnproject.network.ApiService
import com.jadikuli.cnnproject.network.model.LoginRequest
import com.jadikuli.cnnproject.network.model.RegisterRequest

class AuthRepository(
    private val apiService: ApiService
) {
    suspend fun login(email: String, password: String): String? {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.status == "success") {
                response.data?.accessToken
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun register(name: String, email: String, password: String): Boolean {
        return try {
            val response = apiService.register(RegisterRequest(name, email, password))
            response.status == "success"
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}