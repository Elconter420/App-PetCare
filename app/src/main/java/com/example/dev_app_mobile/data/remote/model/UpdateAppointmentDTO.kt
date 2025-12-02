package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class UpdateAppointmentDTO(
    @SerializedName("scheduledAt") val scheduledAt: String? = null,
    @SerializedName("serviceType") val serviceType: String? = null,
    @SerializedName("vetId") val vetId: String? = null,
    @SerializedName("notes") val notes: String? = null,
    @SerializedName("status") val status: String? = null
) {
    companion object {
        fun fromDomain(appointment: com.example.dev_app_mobile.domain.model.Appointment): UpdateAppointmentDTO {
            return UpdateAppointmentDTO(
                scheduledAt = "${appointment.date}T${appointment.time}:00.000Z",
                serviceType = when (appointment.serviceType) {
                    com.example.dev_app_mobile.domain.model.ServiceType.VETERINARY -> "MEDICAL_REVIEW"
                    com.example.dev_app_mobile.domain.model.ServiceType.GROOMING -> "BATH"
                    com.example.dev_app_mobile.domain.model.ServiceType.DAYCARE -> "DAYCARE"
                    com.example.dev_app_mobile.domain.model.ServiceType.TRANSPORT -> "PET_TRANSPORTATION"
                },
                vetId = if (appointment.veterinarianId.isNotEmpty()) appointment.veterinarianId else null,
                notes = appointment.notes,
                status = when (appointment.status) {
                    com.example.dev_app_mobile.domain.model.AppointmentStatus.CONFIRMED -> "CONFIRMED"
                    com.example.dev_app_mobile.domain.model.AppointmentStatus.COMPLETED -> "COMPLETED"
                    com.example.dev_app_mobile.domain.model.AppointmentStatus.CANCELLED -> "CANCELLED"
                    else -> "PENDING"
                }
            )
        }
    }
}