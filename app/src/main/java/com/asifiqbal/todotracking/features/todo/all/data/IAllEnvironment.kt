package com.asifiqbal.todotracking.features.todo.all.data

import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface IAllEnvironment {
    val dateTimeProvider: DateTimeProvider
    fun getList(): Flow<List<ToDoList>>
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}
