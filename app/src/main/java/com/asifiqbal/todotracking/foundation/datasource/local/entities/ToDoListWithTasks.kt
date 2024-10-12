package com.asifiqbal.todotracking.foundation.datasource.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ToDoListWithTasks(
    @Embedded val list: ToDoListDb,
    @Relation(
        entity = ToDoTaskDb::class,
        parentColumn = "list_id",
        entityColumn = "task_listId"
    )
    val taskWithSteps: List<ToDoTaskWithSteps>
)
