package com.asifiqbal.todotracking.foundation.datasource.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ToDoTaskWithSteps(
    @Embedded val task: ToDoTaskDb,
    @Relation(
        parentColumn = "task_id",
        entityColumn = "step_taskId"
    )
    val steps: List<ToDoStepDb>
)
