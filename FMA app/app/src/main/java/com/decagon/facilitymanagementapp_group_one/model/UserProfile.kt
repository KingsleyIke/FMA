package com.decagon.facilitymanagementapp_group_one.model

import com.decagon.facilitymanagementapp_group_one.utils.*

data class UserProfile(
    val success: Boolean?,
    val data: UserData,
    val message: String

)

data class UserData(
    val firstName: String?,
    val lastName: String,
    val userName: String?,
    val gender: String? = null,
    val isActiveProfile: Boolean = false,
    val isProfileCompleted: Boolean = false,
    val avatarUrl: String? = null,
    val squad: String? = null,
    val phoneNumber: String?,
    val created_at: String?,
    val updated_at: String?

)

