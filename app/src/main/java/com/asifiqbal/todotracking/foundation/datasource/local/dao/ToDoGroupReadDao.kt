package com.asifiqbal.todotracking.foundation.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoGroupDb
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoGroupWithList
import kotlinx.coroutines.flow.Flow

/**
 * Return the most recent value for To-Do resource, null if it doesn’t exist.
 */
@Dao
interface ToDoGroupReadDao {

    @Query("SELECT * FROM ToDoGroupDb")
    fun getGroup(): Flow<List<ToDoGroupDb>>

    @Query("SELECT * FROM ToDoGroupDb WHERE group_id = :groupId")
    fun getGroup(groupId: String): Flow<ToDoGroupDb>

    @Transaction
    @Query("SELECT * FROM ToDoGroupDb")
    fun getGroupWithList(): Flow<List<ToDoGroupWithList>>

}
