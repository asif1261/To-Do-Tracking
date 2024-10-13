package com.asifiqbal.todotracking.features.todo.step.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asifiqbal.todotracking.foundation.theme.AlphaDisabled
import com.asifiqbal.todotracking.foundation.uicomponent.PgButton
import com.asifiqbal.todotracking.foundation.uicomponent.PgModalLayout
import com.asifiqbal.todotracking.foundation.uicomponent.PgModalTitle
import com.asifiqbal.todotracking.foundation.uiextension.requestFocusImeAware
import com.asifiqbal.todotracking.R
import kotlinx.coroutines.launch

@Composable
fun UpdateTaskNoteScreen(
    viewModel: StepViewModel,
    onClickBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequest = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        launch { focusRequest.requestFocusImeAware() }
        viewModel.dispatch(StepAction.NoteAction.OnShow)
    }

    PgModalLayout(
        title = {
            PgModalTitle(stringResource(R.string.todo_note))
        },
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Box {
                    BasicTextField(
                        value = state.editNote,
                        onValueChange = { viewModel.dispatch(StepAction.NoteAction.ChangeNote(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .focusRequester(focusRequest),
                        textStyle = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurface),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    )

                    if (state.editNote.text.isBlank()) {
                        Text(
                            text = stringResource(R.string.todo_add_note),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
                        )
                    }
                }

                Spacer(Modifier.size(16.dp))

                PgButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClickBack,
                ) {
                    Text(
                        text = stringResource(R.string.todo_done),
                        color = Color.White
                    )
                }
            }
        }
    }
}
