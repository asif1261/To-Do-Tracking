package com.asifiqbal.todotracking.features.todo.main.data

import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoGroupProvider
import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoListProvider
import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoTaskProvider
import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.model.ToDoGroup
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTaskOverallCount
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

class ToDoMainEnvironment @Inject constructor(
    override val dateTimeProvider: DateTimeProvider,
    private val toDoGroupProvider: ToDoGroupProvider,
    private val toDoListProvider: ToDoListProvider,
    private val toDoTaskProvider: ToDoTaskProvider,
) : IToDoMainEnvironment {

    override fun getGroup(): Flow<List<ToDoGroup>> {
        return toDoGroupProvider.getGroupWithList()
    }

    override fun getOverallCount(): Flow<ToDoTaskOverallCount> {
        val tomorrow = LocalDateTime.of(dateTimeProvider.now().toLocalDate().plusDays(1), LocalTime.MIN)
        return toDoTaskProvider.getOverallCount(tomorrow)
    }

    override suspend fun deleteList(list: ToDoList) {
        toDoListProvider.deleteListById(list.id)
    }

}
