package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.PetResponseDTO
import com.example.dev_app_mobile.data.remote.model.CreatePetDto
import com.example.dev_app_mobile.data.remote.model.UpdatePetDto
import retrofit2.http.*

interface PetService {

    @GET("pets")
    suspend fun getPets(): List<PetResponseDTO>

    @GET("pets/owner/{ownerId}")
    suspend fun getPetsByOwner(@Path("ownerId") ownerId: String): List<PetResponseDTO>

    @GET("pets/{id}")
    suspend fun getPetById(@Path("id") id: String): PetResponseDTO

    @POST("pets")
    suspend fun createPet(@Body createPetDto: CreatePetDto): PetResponseDTO

    @PUT("pets/{id}")
    suspend fun updatePet(
        @Path("id") id: String,
        @Body updatePetDto: UpdatePetDto
    ): PetResponseDTO

    @DELETE("pets/{id}")
    suspend fun deletePet(@Path("id") id: String)
}