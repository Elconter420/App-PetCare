package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.UpdateUserDTO
import com.example.dev_app_mobile.data.remote.model.UserResponseDTO
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("users/me")
    suspend fun getCurrentUser(): Response<UserResponseDTO>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): Response<UserResponseDTO>

    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body updateUserDTO: UpdateUserDTO
    ): Response<UserResponseDTO>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Unit>

    @PUT("users/{id}/password")
    suspend fun changePassword(
        @Path("id") id: String,
        @Body passwordChange: PasswordChangeDTO
    ): Response<Unit>

    @GET("users/{id}/pets")
    suspend fun getUserPets(@Path("id") id: String): Response<List<com.example.dev_app_mobile.data.remote.model.PetResponseDTO>>

    @GET("users/veterinarians")
    suspend fun getVeterinarians(): Response<List<UserResponseDTO>>
}

// DTO adicional para cambio de contraseña
data class PasswordChangeDTO(
    @field:com.google.gson.annotations.SerializedName("currentPassword")
    val currentPassword: String,

    @field:com.google.gson.annotations.SerializedName("newPassword")
    val newPassword: String
)