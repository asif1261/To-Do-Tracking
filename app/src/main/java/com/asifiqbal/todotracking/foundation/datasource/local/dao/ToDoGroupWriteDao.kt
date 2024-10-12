package com.asifiqbal.todotracking.foundation.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoGroupDb
import java.time.LocalDateTime

@Dao
interface ToDoGroupWriteDao {

    @Insert
    suspend fun insertGroup(data: List<ToDoGroupDb>)

    @Delete
    suspend fun deleteGroup(data: List<ToDoGroupDb>)

    @Query("DELETE FROM ToDoGroupDb WHERE group_id = :id")
    suspend fun deleteGroup(id: String)

    @Query("UPDATE ToDoGroupDb SET group_name = :name, group_updatedAt = :updatedAt WHERE group_id = :id")
    suspend fun updateGroupName(id: String, name: String, updatedAt: LocalDateTime)

}
