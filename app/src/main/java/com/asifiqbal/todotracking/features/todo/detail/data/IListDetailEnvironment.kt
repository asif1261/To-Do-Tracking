package com.asifiqbal.todotracking.features.todo.detail.data

import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.foundation.wrapper.IdProvider
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface IListDetailEnvironment {
    val idProvider: IdProvider
    val dateTimeProvider: DateTimeProvider
    fun getListWithTasksById(listId: String): Flow<ToDoList>
    suspend fun createList(list: ToDoList): Flow<ToDoList>
    suspend fun updateList(list: ToDoList): Flow<Any>
    suspend fun createTask(taskName: String, listId: String)
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)

    fun trackSaveListButtonClicked()
}
