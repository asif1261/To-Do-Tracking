package com.asifiqbal.todotracking.model

data class TaskWithList(
    val list: ToDoList,
    val task: ToDoTask
)
