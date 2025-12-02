package com.example.dev_app_mobile.domain.usecase.pets

import com.example.dev_app_mobile.domain.model.Pet
import com.example.dev_app_mobile.domain.repository.PetRepository
import com.example.dev_app_mobile.util.Result
import javax.inject.Inject

class GetPetsUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(ownerId: String): Result<List<Pet>> {
        return petRepository.getPetsByOwner(ownerId) as Result<List<Pet>>
    }
}