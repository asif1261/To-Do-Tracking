package com.asifiqbal.todotracking.features.todo.taskreminder.data

import com.asifiqbal.todotracking.model.TaskWithList
import com.asifiqbal.todotracking.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ITaskReminderEnvironment {
    fun notifyNotification(taskId: String): Flow<TaskWithList>
    fun snoozeReminder(taskId: String): Flow<TaskWithList>
    suspend fun completeReminder(taskId: String): Flow<TaskWithList>
    fun restartAllReminder(): Flow<List<ToDoTask>>
}
