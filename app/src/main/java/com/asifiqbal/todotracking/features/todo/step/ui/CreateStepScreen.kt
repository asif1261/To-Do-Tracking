package com.asifiqbal.todotracking.features.todo.step.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asifiqbal.todotracking.foundation.uicomponent.PgToDoCreator
import com.asifiqbal.todotracking.foundation.uiextension.requestFocusImeAware
import com.asifiqbal.todotracking.R
import kotlinx.coroutines.launch

@Composable
fun CreateStepScreen(
    viewModel: StepViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequest = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        launch { focusRequest.requestFocusImeAware() }
        viewModel.dispatch(StepAction.StepItemAction.Create.OnShow)
    }

    PgToDoCreator(
        value = state.createStepName,
        modifier = Modifier.focusRequester(focusRequest),
        isValid = state.validCreateStepName,
        placeholder = stringResource(R.string.todo_step_next),
        onValueChange = { viewModel.dispatch(StepAction.StepItemAction.Create.ChangeStepName(it)) },
        onSubmit = { viewModel.dispatch(StepAction.StepItemAction.Create.ClickSubmit) },
        onNextSubmit = { viewModel.dispatch(StepAction.StepItemAction.Create.ClickImeDone) }
    )
}
