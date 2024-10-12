package com.asifiqbal.todotracking.features.todo.search.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.asifiqbal.todotracking.model.ToDoTask

sealed class SearchAction {
    data class ChangeSearchText(val text: TextFieldValue) : SearchAction()
    sealed class TaskAction : SearchAction() {
        data class Delete(val task: ToDoTask) : TaskAction()
        data class OnToggleStatus(val task: ToDoTask) : TaskAction()
    }
}
