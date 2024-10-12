package com.asifiqbal.todotracking.foundation.datasource.local.mapper

import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoListDb
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoListWithTasks
import com.asifiqbal.todotracking.model.GroupIdWithList
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTask

fun List<ToDoListDb>.toToDoList(): List<ToDoList> {
    return map { it.toToDoList() }
}

fun List<ToDoListWithTasks>.toDoListWithTasksToToDoList(): List<ToDoList> {
    return map { it.toToDoList() }
}

fun ToDoListWithTasks.toToDoList(): ToDoList {
    return list.toToDoList(taskWithSteps.taskWithStepsToTask())
}

fun ToDoListDb.toToDoList(tasks: List<ToDoTask> = listOf()): ToDoList {
    return ToDoList(
        id = id,
        name = name,
        color = color,
        tasks = tasks,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun List<ToDoList>.toToDoListDb(groupId: String): List<ToDoListDb> {
    return map { it.toToDoListDb(groupId) }
}

fun List<GroupIdWithList>.toToDoListDb(): List<ToDoListDb> {
    return map { it.list.toToDoListDb(it.groupId) }
}

private fun ToDoList.toToDoListDb(groupId: String): ToDoListDb {
    return ToDoListDb(
        id = id,
        name = name,
        color = color,
        groupId = groupId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
