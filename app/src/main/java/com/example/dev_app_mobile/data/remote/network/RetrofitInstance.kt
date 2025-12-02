package com.example.dev_app_mobile.data.remote.network

import com.example.dev_app_mobile.data.remote.model.PetTypeAdapter
import com.example.dev_app_mobile.domain.model.PetType
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://pet-care-api.up.railway.app/api/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Client sin auth para login/register
    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    // Client con auth (se actualizará después del login)
    private var authClient: OkHttpClient = okHttpClient
    private var currentToken: String? = null

    // Retrofit público (para servicios que no requieren auth)
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val gson = GsonBuilder()
        .registerTypeAdapter(PetType::class.java, PetTypeAdapter())
        .create()

    // Función para actualizar el cliente con el token JWT
    fun updateAuthToken(token: String) {
        currentToken = token
        authClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // NUEVA: Función para limpiar el token (logout)
    fun clearAuthToken() {
        currentToken = null
        // Volver al cliente sin auth
        authClient = okHttpClient
    }

    // NUEVA: Obtener token actual
    fun getCurrentToken(): String? = currentToken

    // NUEVA: Verificar si hay token
    fun hasToken(): Boolean = currentToken != null

    // Retrofit con autenticación
    fun getAuthenticatedRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(authClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // NUEVA: Método para crear servicios con auth
    inline fun <reified T> createAuthService(): T {
        return getAuthenticatedRetrofit().create(T::class.java)
    }

    // NUEVA: Método para crear servicios sin auth (para login/register)
    inline fun <reified T> createUnauthService(): T {
        return retrofit.create(T::class.java)
    }
}