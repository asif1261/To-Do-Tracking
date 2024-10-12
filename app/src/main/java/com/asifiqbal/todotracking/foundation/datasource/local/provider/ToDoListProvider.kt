package com.asifiqbal.todotracking.foundation.datasource.local.provider

import com.asifiqbal.todotracking.foundation.datasource.local.dao.ToDoListReadDao
import com.asifiqbal.todotracking.foundation.datasource.local.dao.ToDoListWriteDao
import com.asifiqbal.todotracking.foundation.datasource.local.mapper.toDoListWithTasksToToDoList
import com.asifiqbal.todotracking.foundation.datasource.local.mapper.toGroupIdWithList
import com.asifiqbal.todotracking.foundation.datasource.local.mapper.toToDoList
import com.asifiqbal.todotracking.foundation.datasource.local.mapper.toToDoListDb
import com.asifiqbal.todotracking.foundation.di.DiName
import com.asifiqbal.todotracking.model.GroupIdWithList
import com.asifiqbal.todotracking.model.ToDoList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Named

class ToDoListProvider @Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val toDoListWriteDao: ToDoListWriteDao,
    private val toDoListReadDao: ToDoListReadDao,
) {

    fun getListWithTasks(): Flow<List<ToDoList>> {
        return toDoListReadDao.getListWithTasks()
            .filterNotNull()
            .map { it.toDoListWithTasksToToDoList() }
            .flowOn(dispatcher)
    }

    fun getList(): Flow<List<ToDoList>> {
        return toDoListReadDao.getList()
            .filterNotNull()
            .map { it.toToDoList() }
            .flowOn(dispatcher)
    }

    fun getListById(listId: String): Flow<ToDoList> {
        return toDoListReadDao.getListById(listId)
            .filterNotNull()
            .map { it.toToDoList() }
            .flowOn(dispatcher)
    }

    fun getListByGroupId(groupId: String): Flow<List<ToDoList>> {
        return toDoListReadDao.getListByGroupId(groupId)
            .filterNotNull()
            .map { it.toToDoList() }
            .flowOn(dispatcher)
    }

    fun getListWithTasksById(listId: String): Flow<ToDoList> {
        return toDoListReadDao.getListWithTasksById(listId)
            .map { it.toToDoList() }
            .flowOn(dispatcher)
    }

    fun getListWithUnGroupList(groupId: String): Flow<List<GroupIdWithList>> {
        return toDoListReadDao.getListWithUnGroupList(groupId)
            .filterNotNull()
            .map { it.toGroupIdWithList() }
            .flowOn(dispatcher)
    }


    suspend fun insertList(data: List<ToDoList>, groupId: String) {
        withContext(dispatcher) {
            toDoListWriteDao.insertList(data.toToDoListDb(groupId))
        }
    }

    suspend fun deleteListById(listId: String) {
        withContext(dispatcher) {
            toDoListWriteDao.deleteListById(listId)
        }
    }

    suspend fun updateList(data: List<GroupIdWithList>) {
        withContext(dispatcher) {
            toDoListWriteDao.updateList(data.toToDoListDb())
        }
    }

    suspend fun updateListNameAndColor(toDoList: ToDoList, updatedAt: LocalDateTime) {
        withContext(dispatcher) {
            toDoListWriteDao.updateListNameAndColor(toDoList.id, toDoList.name, toDoList.color, updatedAt)
        }
    }

}
