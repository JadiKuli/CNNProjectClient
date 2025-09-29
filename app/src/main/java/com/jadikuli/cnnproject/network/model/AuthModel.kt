package com.jadikuli.cnnproject.network.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
)

// Response
data class LoginData(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String
)
typealias LoginResponse = BaseResponse<LoginData>

data class RegisterData(
    val name: String,
    val email: String,
    @SerializedName("phone_number") val phoneNumber: String
)
typealias RegisterResponse = BaseResponse<RegisterData>