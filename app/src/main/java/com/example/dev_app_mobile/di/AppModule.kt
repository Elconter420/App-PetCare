package com.example.dev_app_mobile.di

import com.example.dev_app_mobile.data.remote.api.AuthService
import com.example.dev_app_mobile.data.remote.api.PetService
import com.example.dev_app_mobile.data.remote.api.AppointmentService
import com.example.dev_app_mobile.data.remote.api.VeterinarianService
import com.example.dev_app_mobile.data.remote.api.SubscriptionService
import com.example.dev_app_mobile.data.remote.mapper.AppointmentMapper
import com.example.dev_app_mobile.data.remote.network.AppointmentRepositoryImpl
import com.example.dev_app_mobile.data.remote.network.UserRepositoryImpl
import com.example.dev_app_mobile.data.remote.network.PetRepositoryImpl
import com.example.dev_app_mobile.domain.repository.UserRepository
import com.example.dev_app_mobile.domain.repository.PetRepository
import com.example.dev_app_mobile.domain.repository.AppointmentRepository
import com.example.dev_app_mobile.data.remote.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return RetrofitInstance.retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun providePetService(): PetService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(PetService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppointmentService(): AppointmentService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(AppointmentService::class.java)
    }

    @Provides
    @Singleton
    fun provideVeterinarianService(): VeterinarianService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(VeterinarianService::class.java)
    }

    @Provides
    @Singleton
    fun provideSubscriptionService(): SubscriptionService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(SubscriptionService::class.java)
    }

    // NUEVO: Proveer el AppointmentMapper
    @Provides
    @Singleton
    fun provideAppointmentMapper(): AppointmentMapper {
        return AppointmentMapper()
    }

    @Provides
    @Singleton
    fun provideUserRepository(authService: AuthService): UserRepository {
        return UserRepositoryImpl(authService)
    }

    @Provides
    @Singleton
    fun providePetRepository(petService: PetService): PetRepository {
        return PetRepositoryImpl(petService)
    }

    // CORREGIDO: Ahora con 2 parámetros
    @Provides
    @Singleton
    fun provideAppointmentRepository(
        appointmentService: AppointmentService,
        mapper: AppointmentMapper  // ← AÑADIR este parámetro
    ): AppointmentRepository {
        return AppointmentRepositoryImpl(appointmentService, mapper) // ← Pasar ambos
    }
}