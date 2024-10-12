package com.asifiqbal.todotracking.features.todo.step.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asifiqbal.todotracking.foundation.uicomponent.PgToDoCreateConfirmator
import com.asifiqbal.todotracking.foundation.uiextension.requestFocusImeAware
import com.wisnu.kurniawan.composetodolist.R
import kotlinx.coroutines.launch

@Composable
fun RenameTaskScreen(
    viewModel: StepViewModel,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequest = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        launch { focusRequest.requestFocusImeAware() }
        viewModel.dispatch(StepAction.TaskAction.OnShow)
    }

    PgToDoCreateConfirmator(
        title = stringResource(R.string.todo_rename_task),
        positiveText = stringResource(R.string.todo_rename),
        name = state.editTaskName,
        placeholder = stringResource(R.string.todo_rename_task),
        isValidName = state.validEditTaskName,
        focusRequester = focusRequest,
        onNameChange = { viewModel.dispatch(StepAction.TaskAction.ChangeTaskName(it)) },
        onCancelClick = onCancelClick,
        onSaveClick = {
            viewModel.dispatch(StepAction.TaskAction.ClickSave)
            onSaveClick()
        }
    )
}
