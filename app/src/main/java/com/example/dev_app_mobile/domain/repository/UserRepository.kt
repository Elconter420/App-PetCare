package com.example.dev_app_mobile.domain.repository

import com.example.dev_app_mobile.domain.model.User
import com.example.dev_app_mobile.util.Result

interface UserRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(
        name: String,
        email: String,
        password: String,
        phone: String?,
        address: String?
    ): Result<User>

    suspend fun logout(): Result<Boolean>
    suspend fun getCurrentUser(): Result<User>
    suspend fun updateUser(
        userId: String,
        name: String?,
        email: String?,
        phone: String?,
        address: String?
    ): Result<User>

    suspend fun deleteUser(userId: String): Result<Boolean>
    suspend fun isLoggedIn(): Boolean
    suspend fun refreshToken(): Result<String>
}