package com.asifiqbal.todotracking.features.todo.group.data

import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.foundation.wrapper.IdProvider
import com.asifiqbal.todotracking.model.ToDoGroup
import kotlinx.coroutines.flow.Flow

interface ICreateGroupEnvironment {
    val idProvider: IdProvider
    val dateTimeProvider: DateTimeProvider
    suspend fun getGroup(groupId: String): Flow<ToDoGroup>
    suspend fun createGroup(name: String): Flow<String>
    suspend fun renameGroup(groupId: String, name: String): Flow<Any>
}
