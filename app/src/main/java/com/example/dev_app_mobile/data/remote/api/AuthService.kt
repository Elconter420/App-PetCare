package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.AuthResponseDTO
import com.example.dev_app_mobile.data.remote.model.LoginDTO
import com.example.dev_app_mobile.data.remote.model.RegisterDTO
import com.example.dev_app_mobile.data.remote.model.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginDTO: LoginDTO): Response<AuthResponseDTO>

    @POST("auth/register")
    suspend fun register(@Body registerDTO: RegisterDTO): Response<AuthResponseDTO>

    @GET("auth/profile")
    suspend fun getProfile(): Response<UserResponseDTO>
}