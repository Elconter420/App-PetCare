package com.example.dev_app_mobile.data.remote.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.dev_app_mobile.data.remote.api.PetService
import com.example.dev_app_mobile.data.remote.model.CreatePetDto
import com.example.dev_app_mobile.data.remote.model.UpdatePetDto
import com.example.dev_app_mobile.domain.model.Pet
import com.example.dev_app_mobile.domain.repository.PetRepository
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val petApiService: PetService
) : PetRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPets(): List<Pet> {
        return try {
            val response = petApiService.getPets()
            response.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPetById(id: String): Pet? {
        return try {
            val response = petApiService.getPetById(id)
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPetsByOwner(ownerId: String): List<Pet> {
        return try {
            val response = petApiService.getPetsByOwner(ownerId)
            response.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createPet(pet: Pet): Result<Pet> {
        return try {
            val createPetDto = CreatePetDto(
                name = pet.name,
                species = pet.type,
                breed = pet.breed,
                age = pet.age,
                weight = pet.weight,
                medicalHistory = pet.medicalHistory
            )

            val response = petApiService.createPet(createPetDto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // IMPLEMENTACIÓN DEL MÉTODO QUE FALTABA
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updatePet(pet: Pet): Result<Pet> {
        return try {
            val updatePetDto = UpdatePetDto(
                name = pet.name,
                species = pet.type,
                breed = pet.breed,
                age = pet.age,
                weight = pet.weight,
                medicalHistory = pet.medicalHistory
            )

            val response = petApiService.updatePet(pet.id, updatePetDto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // IMPLEMENTACIÓN DEL MÉTODO QUE FALTABA
    override suspend fun deletePet(petId: String): Result<Boolean> {
        return try {
            petApiService.deletePet(petId)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}