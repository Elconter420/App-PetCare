package com.example.dev_app_mobile.data.remote.model

import com.example.dev_app_mobile.domain.model.MedicalRecord
import com.example.dev_app_mobile.domain.model.PetType
import com.google.gson.annotations.SerializedName
import com.google.gson.*
import java.lang.reflect.Type

data class CreatePetDto(
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: PetType,
    @SerializedName("breed") val breed: String,
    @SerializedName("age") val age: Int? = null,
    @SerializedName("weight") val weight: Double? = null,
    @SerializedName("photo") val photo: String? = null,
    @SerializedName("notes") val notes: String? = null,
    @SerializedName("medicalHistory") val medicalHistory: List<MedicalRecord> = emptyList()
) {
    companion object {
        fun fromDomain(pet: com.example.dev_app_mobile.domain.model.Pet): CreatePetDto {
            return CreatePetDto(
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

// TypeAdapter para PetType
class PetTypeAdapter : JsonSerializer<PetType>, JsonDeserializer<PetType> {
    override fun serialize(
        src: PetType,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.name)  // Convertir a string "DOG", "CAT", etc.
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): PetType {
        return PetType.valueOf(json.asString)  // Convertir de string a enum
    }
}