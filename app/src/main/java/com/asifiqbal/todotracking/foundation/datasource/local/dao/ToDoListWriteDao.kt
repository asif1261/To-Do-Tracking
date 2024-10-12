package com.asifiqbal.todotracking.foundation.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoListDb
import com.asifiqbal.todotracking.model.ToDoColor
import java.time.LocalDateTime

@Dao
interface ToDoListWriteDao {

    @Insert
    suspend fun insertList(data: List<ToDoListDb>)

    @Delete
    suspend fun deleteList(data: List<ToDoListDb>)

    @Query("DELETE FROM ToDoListDb WHERE list_id = :id")
    suspend fun deleteListById(id: String)

    @Update
    suspend fun updateList(data: List<ToDoListDb>)

    @Query("UPDATE ToDoListDb SET list_name = :name, list_color = :color, list_updatedAt = :updatedAt WHERE list_id = :id")
    suspend fun updateListNameAndColor(id: String, name: String, color: ToDoColor, updatedAt: LocalDateTime)

    @Query("UPDATE ToDoListDb SET list_groupId = :groupId, list_updatedAt = :updatedAt WHERE list_id IN (:ids)")
    suspend fun updateListGroup(ids: List<String>, groupId: String, updatedAt: LocalDateTime)

}
