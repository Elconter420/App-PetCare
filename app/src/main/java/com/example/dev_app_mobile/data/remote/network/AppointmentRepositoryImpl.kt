package com.example.dev_app_mobile.data.remote.network

import com.example.dev_app_mobile.data.remote.model.CreateAppointmentDTO
import com.example.dev_app_mobile.data.remote.model.UpdateAppointmentDTO
import com.example.dev_app_mobile.data.remote.api.AppointmentService
import com.example.dev_app_mobile.data.remote.mapper.AppointmentMapper
import com.example.dev_app_mobile.domain.model.Appointment
import com.example.dev_app_mobile.domain.repository.AppointmentRepository
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val appointmentApiService: AppointmentService,
    private val mapper: AppointmentMapper
) : AppointmentRepository {

    override suspend fun getAppointments(): List<Appointment> {
        return try {
            val response = appointmentApiService.getAppointments()
            // Convertir DTO a Domain
            response.map { mapper.toDomain(it) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getAppointmentById(id: String): Appointment {
        return try {
            val response = appointmentApiService.getAppointmentById(id)
            mapper.toDomain(response)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun createAppointment(appointment: CreateAppointmentDTO): Appointment {
        return try {
            val response = appointmentApiService.createAppointment(appointment)
            mapper.toDomain(response)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateAppointment(
        id: String,
        updateAppointmentDTO: UpdateAppointmentDTO
    ): Appointment {
        return try {
            val response = appointmentApiService.updateAppointment(id, updateAppointmentDTO)
            mapper.toDomain(response)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun cancelAppointment(id: String): Boolean {
        return try {
            appointmentApiService.cancelAppointment(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getVeterinarianAvailableSlots(
        veterinarianId: String,
        date: String
    ): List<String> {
        return try {
            appointmentApiService.getVeterinarianAvailableSlots(veterinarianId, date)
        } catch (e: Exception) {
            emptyList()
        }
    }
}