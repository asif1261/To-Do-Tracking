package com.asifiqbal.todotracking.features.todo.grouplist.data

import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.model.GroupIdWithList
import kotlinx.coroutines.flow.Flow

interface IUpdateGroupListEnvironment {
    val dateTimeProvider: DateTimeProvider
    fun getListWithUnGroupList(groupId: String): Flow<List<GroupIdWithList>>
    suspend fun updateList(data: List<GroupIdWithList>)
}
