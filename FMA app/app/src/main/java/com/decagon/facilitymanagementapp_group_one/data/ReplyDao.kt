package com.decagon.facilitymanagementapp_group_one.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decagon.facilitymanagementapp_group_one.model.Reply

/**
 * Holds the queries to the database
 * */

@Dao
interface ReplyDao {

    @Query("SELECT * FROM reply_table WHERE id = :id")
    fun getAllReplies(id : Int) : LiveData<List<Reply>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReplies(comment: List<Reply>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReply(reply: Reply)

}