package com.asifiqbal.todotracking.features.todo.grouplist.ui

import androidx.compose.runtime.Immutable
import com.asifiqbal.todotracking.model.GroupIdWithList

@Immutable
data class UpdateGroupListState(
    val initialItems: List<GroupIdWithList> = listOf(),
    val items: List<GroupIdWithList> = listOf()
) {
    val isChanges = initialItems != items
}
