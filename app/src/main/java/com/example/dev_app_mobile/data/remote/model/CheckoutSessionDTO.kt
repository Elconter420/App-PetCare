package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class CheckoutSessionDTO(
    @SerializedName("sessionId") val sessionId: String,
    @SerializedName("checkoutUrl") val checkoutUrl: String
)