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
import com.asifiqbal.todotracking.R
import kotlinx.coroutines.launch

@Composable
fun RenameStepScreen(
    viewModel: StepViewModel,
    stepId: String,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequest = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        launch { focusRequest.requestFocusImeAware() }
        viewModel.dispatch(StepAction.StepItemAction.Edit.OnShow(stepId))
    }

    PgToDoCreateConfirmator(
        title = stringResource(R.string.todo_step_rename),
        positiveText = stringResource(R.string.todo_rename),
        name = state.editStepName,
        placeholder = stringResource(R.string.todo_step_rename),
        isValidName = state.validEditStepName,
        focusRequester = focusRequest,
        onNameChange = { viewModel.dispatch(StepAction.StepItemAction.Edit.ChangeStepName(it)) },
        onCancelClick = onCancelClick,
        onSaveClick = {
            viewModel.dispatch(StepAction.StepItemAction.Edit.ClickSave(stepId))
            onSaveClick()
        }
    )
}
