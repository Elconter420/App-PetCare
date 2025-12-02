package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class SubscriptionResponseDTO(
    @SerializedName("id") val id: String,
    @SerializedName("plan") val plan: String,
    @SerializedName("status") val status: String,
    @SerializedName("startAt") val startAt: String,
    @SerializedName("endAt") val endAt: String,
    @SerializedName("cancelAtEnd") val cancelAtEnd: Boolean,
    @SerializedName("createdAt") val createdAt: String
)