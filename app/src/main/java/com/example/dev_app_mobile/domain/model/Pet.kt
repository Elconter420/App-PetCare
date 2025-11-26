package com.example.dev_app_mobile.domain.model

data class Pet(
    val id: String,
    val name: String,
    val type: PetType,
    val breed: String,
    val age: Int,
    val weight: Double,
    val ownerId: String,
    val medicalHistory: List<MedicalRecord> = emptyList(),
    val profileImage: String? = null
)

enum class PetType {
    DOG, CAT, BIRD, OTHER
}

data class MedicalRecord(
    val date: String,
    val description: String,
    val veterinarian: String
)