package com.example.dev_app_mobile.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val role: UserRole,
    val profileImage: String? = null,
    val isActive: Boolean = true
)

enum class UserRole {
    USER, VETERINARIAN, ADMIN
}