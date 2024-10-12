package com.asifiqbal.todotracking.features.todo.main.data

import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.model.ToDoGroup
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTaskOverallCount
import kotlinx.coroutines.flow.Flow

interface IToDoMainEnvironment {
    val dateTimeProvider: DateTimeProvider
    fun getGroup(): Flow<List<ToDoGroup>>
    fun getOverallCount(): Flow<ToDoTaskOverallCount>
    suspend fun deleteList(list: ToDoList)
}
