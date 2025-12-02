package com.example.dev_app_mobile.data.remote.model

import com.example.dev_app_mobile.domain.model.User
import com.example.dev_app_mobile.domain.model.UserRole
import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("role") val role: String? = null,
    @SerializedName("profileImage") val profileImage: String? = null
) {
    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            phone = phone,
            address = address,
            role = when (role) {
                "ADMIN" -> UserRole.ADMIN
                "VETERINARIAN" -> UserRole.VETERINARIAN
                else -> UserRole.USER
            },
            profileImage = profileImage
        )
    }
}