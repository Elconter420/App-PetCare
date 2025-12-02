package com.example.dev_app_mobile.data.remote.mapper

import com.example.dev_app_mobile.data.remote.model.AppointmentResponseDTO
import com.example.dev_app_mobile.domain.model.Appointment

class AppointmentMapper {
    fun toDomain(dto: AppointmentResponseDTO): Appointment {
        return Appointment(
            id = dto.id,
            petId = dto.pet.id,
            veterinarianId = dto.veterinarian?.id ?: "",
            serviceType = when (dto.serviceType) {
                "MEDICAL_REVIEW", "FOLLOW_UP" -> com.example.dev_app_mobile.domain.model.ServiceType.VETERINARY
                "BATH", "HAIRCUT" -> com.example.dev_app_mobile.domain.model.ServiceType.GROOMING
                "DAYCARE" -> com.example.dev_app_mobile.domain.model.ServiceType.DAYCARE
                "PET_TRANSPORTATION" -> com.example.dev_app_mobile.domain.model.ServiceType.TRANSPORT
                else -> com.example.dev_app_mobile.domain.model.ServiceType.VETERINARY
            },
            date = dto.scheduledAt.split("T")[0],
            time = dto.scheduledAt.split("T")[1].substring(0, 5),
            status = when (dto.status?.uppercase()) {
                "CONFIRMED" -> com.example.dev_app_mobile.domain.model.AppointmentStatus.CONFIRMED
                "COMPLETED" -> com.example.dev_app_mobile.domain.model.AppointmentStatus.COMPLETED
                "CANCELLED" -> com.example.dev_app_mobile.domain.model.AppointmentStatus.CANCELLED
                else -> com.example.dev_app_mobile.domain.model.AppointmentStatus.PENDING
            },
            notes = dto.notes
        )
    }
}