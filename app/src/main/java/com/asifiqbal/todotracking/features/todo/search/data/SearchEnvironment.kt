package com.asifiqbal.todotracking.features.todo.search.data

import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoTaskProvider
import com.asifiqbal.todotracking.foundation.extension.sanitizeQuery
import com.asifiqbal.todotracking.foundation.extension.toggleStatusHandler
import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchEnvironment @Inject constructor(
    private val toDoTaskProvider: ToDoTaskProvider,
    private val dateTimeProvider: DateTimeProvider
) : ISearchEnvironment {

    override fun searchList(query: String): Flow<List<ToDoList>> {
        return if (query.isNotBlank()) {
            val wildcardQuery = query.sanitizeQuery()
            toDoTaskProvider.searchTaskWithList(wildcardQuery)
                .map {
                    it.groupBy { taskWithList -> taskWithList.list.id }
                        .map { (_, value) ->
                            val tasks = value.map { taskWithList -> taskWithList.task }
                            val list = value.map { taskWithList -> taskWithList.list }.first()
                            list.copy(tasks = tasks)
                        }
                        .sortedBy { task -> task.name }
                }
        } else {
            flow { emit(emptyList<ToDoList>()) }
        }
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
