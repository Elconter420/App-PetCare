package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.AuthResponseDTO
import com.example.dev_app_mobile.data.remote.model.LoginDTO
import com.example.dev_app_mobile.data.remote.model.RegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login(@Body loginDTO: LoginDTO): Response<AuthResponseDTO>

    @POST("auth/register")
    suspend fun register(@Body registerDTO: RegisterDTO): Response<AuthResponseDTO>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body refreshToken: RefreshTokenDTO
    ): Response<AuthResponseDTO>

    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Body forgotPasswordDTO: ForgotPasswordDTO
    ): Response<Unit>

    @POST("auth/reset-password")
    suspend fun resetPassword(
        @Body resetPasswordDTO: ResetPasswordDTO
    ): Response<Unit>
}

// DTOs adicionales para Auth
data class RefreshTokenDTO(
    @field:com.google.gson.annotations.SerializedName("refreshToken")
    val refreshToken: String
)

data class ForgotPasswordDTO(
    @field:com.google.gson.annotations.SerializedName("email")
    val email: String
)

data class ResetPasswordDTO(
    @field:com.google.gson.annotations.SerializedName("token")
    val token: String,

    @field:com.google.gson.annotations.SerializedName("newPassword")
    val newPassword: String
)