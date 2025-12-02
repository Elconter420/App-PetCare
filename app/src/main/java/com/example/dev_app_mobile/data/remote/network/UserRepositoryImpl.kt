package com.example.dev_app_mobile.data.remote.network

import com.example.dev_app_mobile.data.remote.api.AuthService
import com.example.dev_app_mobile.data.remote.model.LoginDTO
import com.example.dev_app_mobile.data.remote.model.RegisterDTO
import com.example.dev_app_mobile.domain.model.User
import com.example.dev_app_mobile.domain.repository.UserRepository
import com.example.dev_app_mobile.util.Result
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    // Para AuthService, usa el retrofit sin auth
    private val authService: AuthService
) : UserRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = authService.login(LoginDTO(email, password))
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!

                // Actualizar Retrofit con el nuevo token
                RetrofitInstance.updateAuthToken(authResponse.accessToken)

                Result.Success(authResponse.user.toDomain())
            } else {
                val errorBody = response.errorBody()?.string() ?: response.message()
                Result.Error("Login failed: $errorBody")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        phone: String?,
        address: String?
    ): Result<User> {
        return try {
            val response = authService.register(RegisterDTO(name, email, password, phone, address))
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!

                // Actualizar Retrofit con el nuevo token
                RetrofitInstance.updateAuthToken(authResponse.accessToken)

                Result.Success(authResponse.user.toDomain())
            } else {
                val errorBody = response.errorBody()?.string() ?: response.message()
                Result.Error("Registration failed: $errorBody")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }

    override suspend fun logout(): Result<Boolean> {
        return try {
            // Intentar logout en el servidor
            val response = authService.logout()

            // Limpiar token localmente SIEMPRE
            RetrofitInstance.clearAuthToken()

            if (response.isSuccessful) {
                Result.Success(true)
            } else {
                // Aunque falle el logout remoto, limpiamos localmente
                Result.Success(true)
            }
        } catch (e: Exception) {
            // Si hay error de red, igual limpiamos token local
            RetrofitInstance.clearAuthToken()
            Result.Success(true)
        }
    }

    override suspend fun getCurrentUser(): Result<User> {
        return Result.Error("getCurrentUser not implemented yet")
    }

    override suspend fun updateUser(
        userId: String,
        name: String?,
        email: String?,
        phone: String?,
        address: String?
    ): Result<User> {
        return Result.Error("updateUser not implemented yet")
    }

    override suspend fun deleteUser(userId: String): Result<Boolean> {
        return Result.Error("deleteUser not implemented yet")
    }

    override suspend fun isLoggedIn(): Boolean {
        // Verificar si hay token guardado
        return RetrofitInstance.hasToken()
    }

    override suspend fun refreshToken(): Result<String> {
        return Result.Error("refreshToken not implemented yet")
    }
}