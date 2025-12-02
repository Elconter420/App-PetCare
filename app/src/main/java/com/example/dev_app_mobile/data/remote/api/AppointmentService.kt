package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.AppointmentResponseDTO
import com.example.dev_app_mobile.data.remote.model.CreateAppointmentDTO
import com.example.dev_app_mobile.data.remote.model.UpdateAppointmentDTO
import com.example.dev_app_mobile.data.remote.model.VeterinarianAvailableSlotsDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AppointmentService {
    @GET("appointments")
    suspend fun getAppointments(): List<AppointmentResponseDTO>

    @GET("appointments/{id}")
    suspend fun getAppointmentById(@Path("id") id: String): AppointmentResponseDTO

    @POST("appointments")
    suspend fun createAppointment(@Body appointment: CreateAppointmentDTO): AppointmentResponseDTO

    @PUT("appointments/{id}")
    suspend fun updateAppointment(
        @Path("id") id: String,
        @Body updateAppointmentDTO: UpdateAppointmentDTO
    ): AppointmentResponseDTO

    @DELETE("appointments/{id}")
    suspend fun cancelAppointment(@Path("id") id: String)

    @GET("veterinarians/{id}/available-slots")
    suspend fun getVeterinarianAvailableSlots(
        @Path("id") id: String,
        @Query("date") date: String
    ): List<String>
}