package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.CheckoutSessionDTO
import com.example.dev_app_mobile.data.remote.model.SubscriptionResponseDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface SubscriptionService {
    @GET("subscriptions/my/current")
    suspend fun getCurrentSubscription(): Response<SubscriptionResponseDTO>

    @PATCH("subscriptions/my/current")
    suspend fun reactivateSubscription(): Response<SubscriptionResponseDTO>

    @DELETE("subscriptions/my/current")
    suspend fun cancelSubscription(): Response<Unit>

    @GET("subscriptions/checkout-session/{plan}")
    suspend fun createCheckoutSession(@Path("plan") plan: String): Response<CheckoutSessionDTO>
}