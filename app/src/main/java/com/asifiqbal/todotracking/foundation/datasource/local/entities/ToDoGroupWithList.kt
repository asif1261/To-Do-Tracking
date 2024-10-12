package com.asifiqbal.todotracking.foundation.datasource.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ToDoGroupWithList(
    @Embedded val group: ToDoGroupDb,
    @Relation(
        entity = ToDoListDb::class,
        parentColumn = "group_id",
        entityColumn = "list_groupId"
    )
    val listWithTasks: List<ToDoListWithTasks>
)
