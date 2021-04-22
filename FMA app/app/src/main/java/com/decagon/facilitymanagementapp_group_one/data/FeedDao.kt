package com.decagon.facilitymanagementapp_group_one.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.facilitymanagementapp_group_one.model.Feed

/**
 * Holds the queries to the database
 * */

@Dao
interface FeedDao {

    @Query("SELECT * FROM feeds_table")
    fun getAllFeeds() : LiveData<List<Feed>>

//    @Query("SELECT * FROM feeds_table ORDER BY id DESC ")
//    fun getAllFeeds() : LiveData<List<Feed>>

    @Query("SELECT * FROM feeds_table WHERE id = :id")
    fun getFeed(id: Int): LiveData<Feed>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFeed(vararg feed: Feed)

    @Query("DELETE FROM feeds_table WHERE id = :id")
    suspend fun deleteFeed(id: String)
}