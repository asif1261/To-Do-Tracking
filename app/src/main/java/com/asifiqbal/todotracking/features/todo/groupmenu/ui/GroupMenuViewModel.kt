package com.asifiqbal.todotracking.features.todo.groupmenu.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ClearAll
import androidx.compose.material.icons.rounded.Delete
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.asifiqbal.todotracking.features.todo.groupmenu.data.IGroupMenuEnvironment
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoGroupDb
import com.asifiqbal.todotracking.runtime.navigation.ARG_GROUP_ID
import com.wisnu.foundation.coreviewmodel.StatefulViewModel
import com.wisnu.kurniawan.composetodolist.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupMenuViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    groupMenuEnvironment: IGroupMenuEnvironment,
) :
    StatefulViewModel<GroupMenuState, Unit, GroupMenuAction, IGroupMenuEnvironment>(GroupMenuState(), groupMenuEnvironment) {

    val groupId = savedStateHandle.get<String>(ARG_GROUP_ID)

    init {
        viewModelScope.launch {
            if (!groupId.isNullOrBlank()) {
                delay(100)
                environment.hasList(groupId)
                    .collect {
                        setState { copy(items = initial(it)) }
                    }
            }
        }
    }

    private fun initial(isListEmpty: Boolean): List<GroupMenuItem> {
        val enabled = groupId != ToDoGroupDb.DEFAULT_ID
        return listOf(
            GroupMenuItem.AddRemove(R.string.todo_group_menu_add_remove_list, enabled),
            GroupMenuItem.Rename(R.string.todo_group_menu_rename, enabled),
            GroupMenuItem.Delete(
                if (isListEmpty) {
                    R.string.todo_group_menu_delete
                } else {
                    R.string.todo_group_menu_ungroup
                },
                enabled,
                if (isListEmpty) {
                    Icons.Rounded.Delete
                } else {
                    Icons.Rounded.ClearAll
                }
            )
        )
    }

    override fun dispatch(action: GroupMenuAction) {
        when (action) {
            GroupMenuAction.ClickDelete -> {
                viewModelScope.launch {
                    if (!groupId.isNullOrBlank()) {
                        environment.deleteGroup(groupId).collect()
                    }
                }
            }
        }
    }

}
