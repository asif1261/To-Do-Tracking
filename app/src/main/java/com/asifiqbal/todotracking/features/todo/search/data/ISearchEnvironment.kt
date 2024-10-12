package com.asifiqbal.todotracking.features.todo.search.data

import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ISearchEnvironment {
    fun searchList(query: String): Flow<List<ToDoList>>
    suspend fun toggleTaskStatus(toDoTask: ToDoTask)
    suspend fun deleteTask(task: ToDoTask)
}
