package com.asifiqbal.todotracking.foundation.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoStepDb
import com.asifiqbal.todotracking.model.ToDoStatus
import java.time.LocalDateTime

@Dao
interface ToDoStepWriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(data: List<ToDoStepDb>)

    @Delete
    suspend fun deleteStep(data: List<ToDoStepDb>)

    @Query("UPDATE ToDoStepDb SET step_name = :name, step_updatedAt = :updatedAt WHERE step_id = :id")
    suspend fun updateStepName(id: String, name: String, updatedAt: LocalDateTime)

    @Query("UPDATE ToDoStepDb SET step_status = :status, step_updatedAt = :updatedAt WHERE step_id = :id")
    suspend fun updateStepStatus(id: String, status: ToDoStatus, updatedAt: LocalDateTime)

    @Query("DELETE FROM ToDoStepDb WHERE step_id = :id")
    suspend fun deleteStepById(id: String)

}
