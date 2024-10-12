package com.asifiqbal.todotracking.features.todo.all.data

import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoListProvider
import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoTaskProvider
import com.asifiqbal.todotracking.foundation.extension.toggleStatusHandler
import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllEnvironment @Inject constructor(
    override val dateTimeProvider: DateTimeProvider,
    private val toDoListProvider: ToDoListProvider,
    private val toDoTaskProvider: ToDoTaskProvider
) : IAllEnvironment {

    override fun getList(): Flow<List<ToDoList>> {
        return toDoListProvider.getListWithTasks()
    }

    override suspend fun toggleTaskStatus(toDoTask: ToDoTask) {
        val currentDate = dateTimeProvider.now()
        toDoTask.toggleStatusHandler(
            currentDate,
            { completedAt, newStatus ->
                toDoTaskProvider.updateTaskStatus(toDoTask.id, newStatus, completedAt, currentDate)
            },
            { nextDueDate ->
                toDoTaskProvider.updateTaskDueDate(toDoTask.id, nextDueDate, toDoTask.isDueDateTimeSet, currentDate)
            }
        )
    }

    override suspend fun deleteTask(task: ToDoTask) {
        toDoTaskProvider.deleteTaskById(task.id)
    }

}
