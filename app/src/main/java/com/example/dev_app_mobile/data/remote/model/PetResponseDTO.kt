package com.example.dev_app_mobile.data.remote.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

data class PetResponseDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("breed") val breed: String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("weight") val weight: Double?,
    @SerializedName("photo") val photo: String?,
    @SerializedName("notes") val notes: String?,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("ownerId") val ownerId: String? = null
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(): com.example.dev_app_mobile.domain.model.Pet {
        return com.example.dev_app_mobile.domain.model.Pet(
            id = id,
            name = name,
            type = when (species.uppercase()) {
                "CAT" -> com.example.dev_app_mobile.domain.model.PetType.CAT
                "RABBIT" -> com.example.dev_app_mobile.domain.model.PetType.OTHER
                "BIRD" -> com.example.dev_app_mobile.domain.model.PetType.BIRD
                "OTHER" -> com.example.dev_app_mobile.domain.model.PetType.OTHER
                else -> com.example.dev_app_mobile.domain.model.PetType.DOG
            },
            breed = breed,
            age = calculateAge(birthDate),
            weight = weight ?: 0.0,
            ownerId = ownerId ?: "",
            medicalHistory = emptyList(),
            profileImage = photo
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(birthDate: String): Int {
        return try {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            val birth = LocalDate.parse(birthDate.split("T")[0], formatter)
            val current = LocalDate.now()
            Period.between(birth, current).years
        } catch (e: Exception) {
            0
        }
    }
}