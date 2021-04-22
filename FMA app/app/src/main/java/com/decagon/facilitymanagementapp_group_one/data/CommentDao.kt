package com.decagon.facilitymanagementapp_group_one.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decagon.facilitymanagementapp_group_one.model.Comment

/**
 * Holds the queries to the database
 * */

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments_table WHERE id = :id")
    fun getAllComments(id : Int) : LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: Comment)

}