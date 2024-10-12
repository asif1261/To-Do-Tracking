package com.asifiqbal.todotracking.foundation.datasource.local.provider

import com.asifiqbal.todotracking.foundation.datasource.local.dao.ToDoStepWriteDao
import com.asifiqbal.todotracking.foundation.datasource.local.mapper.toStepDb
import com.asifiqbal.todotracking.foundation.di.DiName
import com.asifiqbal.todotracking.model.ToDoStatus
import com.asifiqbal.todotracking.model.ToDoStep
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Named

class ToDoStepProvider @Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val toDoStepWriteDao: ToDoStepWriteDao,
) {

    suspend fun insertStep(data: List<ToDoStep>, taskId: String) {
        withContext(dispatcher) {
            toDoStepWriteDao.insertStep(data.toStepDb(taskId))
        }
    }

    suspend fun updateStepStatus(id: String, status: ToDoStatus, updatedAt: LocalDateTime) {
        withContext(dispatcher) {
            toDoStepWriteDao.updateStepStatus(id, status, updatedAt)
        }
    }

    suspend fun updateStepName(id: String, name: String, updatedAt: LocalDateTime) {
        withContext(dispatcher) {
            toDoStepWriteDao.updateStepName(id, name, updatedAt)
        }
    }

    suspend fun deleteStepById(id: String) {
        withContext(dispatcher) {
            toDoStepWriteDao.deleteStepById(id)
        }
    }

}
