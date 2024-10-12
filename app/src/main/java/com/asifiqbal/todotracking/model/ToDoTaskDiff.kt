package com.asifiqbal.todotracking.model

data class ToDoTaskDiff(
    val addedTask: Map<String, ToDoTask>,
    val deletedTask: Map<String, ToDoTask>,
    val modifiedTask: Map<String, ToDoTask>,
)
