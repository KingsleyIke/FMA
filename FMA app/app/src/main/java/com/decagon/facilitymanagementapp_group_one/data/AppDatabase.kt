package com.decagon.facilitymanagementapp_group_one.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.decagon.facilitymanagementapp_group_one.model.*

/**
 * Local data source for storing and managing the data generated in the app
 * */

@Database(entities = [Feed::class, Reply::class, Comment::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedsDao(): FeedDao
    abstract fun repliesDao(): ReplyDao
    abstract fun commentsDao(): CommentDao
}