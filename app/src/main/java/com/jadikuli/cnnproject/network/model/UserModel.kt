package com.jadikuli.cnnproject.network.model

import com.google.gson.annotations.SerializedName

data class ProfileData(
    val name: String,
    val email: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("profile_photo_path") val profilePhoto: String?
)
typealias ProfileResponse = BaseResponse<ProfileData>