package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class UpdateUserDTO(
    @SerializedName("name") val name: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("profileImage") val profileImage: String? = null
)