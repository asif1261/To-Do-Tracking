package com.asifiqbal.todotracking.features.todo.scheduled.data

import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.foundation.wrapper.IdProvider
import com.asifiqbal.todotracking.model.TaskWithList
import com.asifiqbal.todotracking.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface IScheduledEnvironment {
    val idProvider: IdProvider
    val dateTimeProvider: DateTimeProvider
    fun getToDoTaskWithStepsOrderByDueDateWithList(maxDate: LocalDateTime? = null): Flow<List<TaskWithList>>
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}
