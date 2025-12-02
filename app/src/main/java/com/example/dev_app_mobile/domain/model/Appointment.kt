package com.example.dev_app_mobile.domain.model

data class Appointment(
    val id: String,
    val petId: String,
    val veterinarianId: String,
    val serviceType: ServiceType,
    val date: String,  // Formato: "YYYY-MM-DD"
    val time: String,  // Formato: "HH:MM"
    val status: AppointmentStatus = AppointmentStatus.PENDING,
    val notes: String? = null
)