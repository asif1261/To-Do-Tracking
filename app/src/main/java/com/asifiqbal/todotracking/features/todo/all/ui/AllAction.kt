package com.asifiqbal.todotracking.features.todo.all.ui

import com.asifiqbal.todotracking.model.ToDoTask

sealed class AllAction {
    sealed class TaskAction : AllAction() {
        data class Delete(val task: ToDoTask) : TaskAction()
        data class OnToggleStatus(val task: ToDoTask) : TaskAction()
    }

    object ToggleCompleteTaskVisibility : AllAction()
}
