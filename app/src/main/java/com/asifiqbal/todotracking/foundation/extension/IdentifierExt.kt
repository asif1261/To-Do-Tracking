package com.asifiqbal.todotracking.foundation.extension

import com.asifiqbal.todotracking.features.todo.all.ui.ItemAllState
import com.asifiqbal.todotracking.features.todo.detail.ui.ToDoTaskItem
import com.asifiqbal.todotracking.features.todo.main.ui.ItemMainState
import com.asifiqbal.todotracking.features.todo.scheduled.ui.ItemScheduledState

fun ToDoTaskItem.identifier() = when (this) {
    is ToDoTaskItem.CompleteHeader -> id
    is ToDoTaskItem.Complete -> toDoTask.id
    is ToDoTaskItem.InProgress -> toDoTask.id
}

fun ItemAllState.identifier() = when (this) {
    is ItemAllState.Task.Complete -> task.id
    is ItemAllState.Task.InProgress -> task.id
    is ItemAllState.List -> list.id
}

fun ItemScheduledState.identifier() = when (this) {
    is ItemScheduledState.Header -> date.toString()
    is ItemScheduledState.Task.Complete -> task.id
    is ItemScheduledState.Task.InProgress -> task.id
}

fun ItemMainState.identifier() = when (this) {
    is ItemMainState.ItemGroup -> group.id
    is ItemMainState.ItemListType -> list.id
}

