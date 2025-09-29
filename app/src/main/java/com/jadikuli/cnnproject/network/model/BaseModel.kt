package com.jadikuli.cnnproject.network.model

data class BaseResponse<T>(
    val status: String,
    val message: String,
    val data: T?
)