package com.decagon.facilitymanagementapp_group_one.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feeds_table")
data class Feed(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,

    @ColumnInfo(name = "time")
    val time: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "lastName")
    val lastName: String,

    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String? = null,

    @ColumnInfo(name = "question")
    val question: String,

    @ColumnInfo(name = "squad")
    val squad: String,

    @ColumnInfo(name = "type")
    val type: String
)

fun ComplaintResponse.get(): Array<Feed> {
    return data.items.map { item ->
        Feed(
            id = item["id"] as String,
            title = item["title"] as String,
            question = item["question"] as String,
            type = item["type"] as String,
            image = item["image"] as String,
            firstName = item["firstName"] as String,
            lastName = item["lastName"] as String,
            avatarUrl = item["avatarUrl"],
            squad = item["squad"] as String,
            time = 0L
        )
    }.toTypedArray()
}
