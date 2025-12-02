package com.example.dev_app_mobile.domain.repository

import com.example.dev_app_mobile.domain.model.Pet

interface PetRepository {
    suspend fun getPets(): List<Pet>
    suspend fun getPetById(id: String): Pet?
    suspend fun getPetsByOwner(ownerId: String): List<Pet>
    suspend fun createPet(pet: Pet): Result<Pet>
    suspend fun updatePet(pet: Pet): Result<Pet>    // ← FALTANTE
    suspend fun deletePet(petId: String): Result<Boolean> // ← FALTANTE
}