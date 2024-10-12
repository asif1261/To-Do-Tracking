package com.asifiqbal.todotracking.foundation.extension

import com.asifiqbal.todotracking.model.ToDoStatus

fun ToDoStatus.toggle(): ToDoStatus {
    return when (this) {
        ToDoStatus.IN_PROGRESS -> ToDoStatus.COMPLETE
        ToDoStatus.COMPLETE -> ToDoStatus.IN_PROGRESS
    }
}
