package com.example.dev_app_mobile.domain.repository


import com.example.dev_app_mobile.domain.model.Appointment

interface AppointmentRepository {
    suspend fun getAppointments(): List<Appointment>
    suspend fun getAppointmentById(id: String): Appointment
    suspend fun createAppointment(appointment: com.example.dev_app_mobile.data.remote.model.CreateAppointmentDTO): Appointment
    suspend fun updateAppointment(id: String, updateAppointmentDTO: com.example.dev_app_mobile.data.remote.model.UpdateAppointmentDTO): Appointment
    suspend fun cancelAppointment(id: String): Boolean
    suspend fun getVeterinarianAvailableSlots(veterinarianId: String, date: String): List<String>
}