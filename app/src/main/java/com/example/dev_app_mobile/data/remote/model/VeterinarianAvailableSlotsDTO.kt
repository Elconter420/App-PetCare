package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class VeterinarianAvailableSlotsDTO(
    @SerializedName("veterinarianId") val veterinarianId: String,
    @SerializedName("date") val date: String,
    @SerializedName("availableSlots") val availableSlots: List<String>,
    @SerializedName("totalSlots") val totalSlots: Int
)