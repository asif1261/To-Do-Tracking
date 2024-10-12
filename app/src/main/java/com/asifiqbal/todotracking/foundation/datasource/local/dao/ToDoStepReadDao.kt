package com.asifiqbal.todotracking.foundation.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoStepDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoStepReadDao {

    @Query("SELECT * FROM ToDoStepDb")
    fun getStep(): Flow<List<ToDoStepDb>>

    @Query("SELECT * FROM ToDoStepDb WHERE step_taskId = :taskId")
    fun getStep(taskId: String): Flow<List<ToDoStepDb>>

}
