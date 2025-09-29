package com.jadikuli.cnnproject.network

import com.jadikuli.cnnproject.network.model.LoginRequest
import com.jadikuli.cnnproject.network.model.LoginResponse
import com.jadikuli.cnnproject.network.model.RegisterRequest
import com.jadikuli.cnnproject.network.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}