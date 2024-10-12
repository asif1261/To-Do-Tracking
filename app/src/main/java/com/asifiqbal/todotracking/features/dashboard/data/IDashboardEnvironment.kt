package com.asifiqbal.todotracking.features.dashboard.data

import com.asifiqbal.todotracking.model.ToDoTaskDiff
import com.asifiqbal.todotracking.model.User
import kotlinx.coroutines.flow.Flow

interface IDashboardEnvironment {
    fun getUser(): Flow<User>
    fun listenToDoTaskDiff(): Flow<ToDoTaskDiff>
}
