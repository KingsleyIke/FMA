package com.decagon.facilitymanagementapp_group_one.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments_table")
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "post_id") val postId: Int,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "date") val date: Long
)