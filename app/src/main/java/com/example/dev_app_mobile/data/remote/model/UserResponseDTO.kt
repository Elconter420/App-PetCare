package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("createdAt") val createdAt: String
)