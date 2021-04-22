package com.decagon.facilitymanagementapp_group_one.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reply_table")
data class Reply(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "comment_id") val commentId: Int,
    @ColumnInfo(name = "body") val body: String
)