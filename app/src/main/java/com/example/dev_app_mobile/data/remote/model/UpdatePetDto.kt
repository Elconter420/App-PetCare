package com.example.dev_app_mobile.data.remote.model

import com.example.dev_app_mobile.domain.model.MedicalRecord
import com.example.dev_app_mobile.domain.model.PetType
import com.google.gson.annotations.SerializedName

data class UpdatePetDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("species") val species: PetType,
    @SerializedName("breed") val breed: String? = null,
    @SerializedName("age") val age: Int? = null,
    @SerializedName("weight") val weight: Double? = null,
    @SerializedName("photo") val photo: String? = null,
    @SerializedName("medicalHistory") val medicalHistory: List<MedicalRecord> = emptyList()
) {
    companion object {
        fun fromDomain(pet: com.example.dev_app_mobile.domain.model.Pet): UpdatePetDto {
            return UpdatePetDto(
                name = pet.name,
                species = pet.type,
                breed = pet.breed,
                age = pet.age,
                weight = pet.weight,
                photo = pet.profileImage,
                medicalHistory = pet.medicalHistory
            )
        }
    }
}