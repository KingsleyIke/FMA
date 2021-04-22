package com.decagon.facilitymanagementapp_group_one.model

data class Complaint(
    val title : String,
    val question : String,
    val type : String,
    val image : String,
    val isTask : Boolean = true,
    val userId : String
)
