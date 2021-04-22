package com.decagon.facilitymanagementapp_group_one.model

import java.util.*

data class ComplaintResponse(
    val success: Boolean,
    val data: ComplaintData,
    val message: String,
)

data class ComplaintData(
    val totalNumberOfPages: Int,
    val totalNumberOfItems: Int,
    val currentPage: Int,
    val itemsPerPage: Int,
    val items: List<Map<String, String?>>
)