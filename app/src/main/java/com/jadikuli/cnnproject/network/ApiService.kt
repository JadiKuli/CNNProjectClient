package com.jadikuli.cnnproject.network

import com.jadikuli.cnnproject.network.model.ArticleDetailResponse
import com.jadikuli.cnnproject.network.model.ArticleResponse
import com.jadikuli.cnnproject.network.model.HistoryLatestResponse
import com.jadikuli.cnnproject.network.model.HistoryResponse
import com.jadikuli.cnnproject.network.model.LoginRequest
import com.jadikuli.cnnproject.network.model.LoginResponse
import com.jadikuli.cnnproject.network.model.ProfileResponse
import com.jadikuli.cnnproject.network.model.RegisterRequest
import com.jadikuli.cnnproject.network.model.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    // Auth
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    // Article
    @GET("articles")
    suspend fun articles(): ArticleResponse

    @GET("articles/{id}")
    suspend fun getArticleDetail(@Path("id") id: Int): ArticleDetailResponse

    // Profile
    @GET("user")
    suspend fun profile(): ProfileResponse

    // Upload Image
    @Multipart
    @POST("history")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<Unit>

    // History
    @GET("history?sort=latest")
    suspend fun getHistory(): HistoryResponse

    @GET("history/latest")
    suspend fun getLatestHistory(): HistoryLatestResponse
}