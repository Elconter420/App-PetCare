package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class RegisterDTO(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("address") val address: String? = null
)

data class AuthResponseDTO(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("user") val user: UserResponseDTO
)