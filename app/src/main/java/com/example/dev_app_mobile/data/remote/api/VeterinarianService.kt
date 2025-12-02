package com.example.dev_app_mobile.data.remote.api


import com.example.dev_app_mobile.data.remote.model.UserResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface VeterinarianService {
    @GET("users/veterinarians")
    suspend fun getAvailableVeterinarians(): Response<List<UserResponseDTO>>
}