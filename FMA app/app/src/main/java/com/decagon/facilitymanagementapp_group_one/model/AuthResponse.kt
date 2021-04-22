package com.decagon.facilitymanagementapp_group_one.model

data class AuthResponse(
    val success: Boolean,
    val data: Data,
    val message: String
)

data class Data(
    val id: String,
    val isProfileCompleted: Boolean,
    val token: String
)
