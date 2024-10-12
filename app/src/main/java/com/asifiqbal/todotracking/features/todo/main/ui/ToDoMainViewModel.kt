package com.asifiqbal.todotracking.features.todo.main.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.lifecycle.viewModelScope
import com.asifiqbal.todotracking.features.todo.main.data.IToDoMainEnvironment
import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoGroupDb
import com.asifiqbal.todotracking.foundation.theme.MediumRadius
import com.asifiqbal.todotracking.model.ToDoGroup
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoStatus
import com.asifiqbal.todotracking.runtime.navigation.ARG_LIST_ID
import com.asifiqbal.todotracking.runtime.navigation.AllFlow
import com.asifiqbal.todotracking.runtime.navigation.ListDetailFlow
import com.asifiqbal.todotracking.runtime.navigation.MainFlow
import com.asifiqbal.todotracking.runtime.navigation.ScheduledFlow
import com.asifiqbal.todotracking.runtime.navigation.ScheduledTodayFlow
import com.wisnu.foundation.coreviewmodel.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoMainViewModel @Inject constructor(todoMainEnvironment: IToDoMainEnvironment) :
    StatefulViewModel<ToDoMainState, Unit, ToDoMainAction, IToDoMainEnvironment>(ToDoMainState(), todoMainEnvironment) {

    init {
        initToDo()
        initOverallCount()
    }

    override fun dispatch(action: ToDoMainAction) {
        when (action) {
            is ToDoMainAction.DeleteList -> {
                viewModelScope.launch {
                    environment.deleteList(action.itemListType.list)
                }
            }
            is ToDoMainAction.NavBackStackEntryChanged -> {
                viewModelScope.launch {
                    when (action.route) {
                        ListDetailFlow.ListDetailScreen.route -> {
                            val listId = action.arguments?.getString(ARG_LIST_ID)
                            if (listId.isNullOrBlank()) {
                                setState { copy(selectedItemState = SelectedItemState.Empty) }
                            } else {
                                setState { copy(selectedItemState = SelectedItemState.List(listId)) }
                            }
                        }
                        AllFlow.AllScreen.route -> {
                            setState { copy(selectedItemState = SelectedItemState.AllTask) }
                        }
                        ScheduledTodayFlow.ScheduledTodayScreen.route -> {
                            setState { copy(selectedItemState = SelectedItemState.ScheduledTodayTask) }
                        }
                        ScheduledFlow.ScheduledScreen.route -> {
                            setState { copy(selectedItemState = SelectedItemState.ScheduledTask) }
                        }
                        MainFlow.RootEmpty.route -> {
                            setState { copy(selectedItemState = SelectedItemState.Empty) }
                        }
                    }
                }
            }
        }
    }

    private fun initToDo() {
        viewModelScope.launch {
            environment.getGroup()
                .collect {
                    setState {
                        copy(
                            data = it,
                            currentDate = environment.dateTimeProvider.now().dayOfMonth.toString()
                        )
                    }
                }
        }
    }

    private fun initOverallCount() {
        viewModelScope.launch {
            environment.getOverallCount()
                .collect {
                    setState {
                        copy(
                            allTaskCount = it.allTaskCount.toString(),
                            scheduledTodayTaskCount = it.scheduledTodayTaskCount.toString(),
                            scheduledTaskCount = it.scheduledTaskCount.toString(),
                        )
                    }
                }
        }
    }

}

fun ItemMainState.ItemListType.cellShape() = when (this) {
    is ItemMainState.ItemListType.First -> {
        RoundedCornerShape(
            topStart = MediumRadius,
            topEnd = MediumRadius
        )
    }
    is ItemMainState.ItemListType.Last -> {
        RoundedCornerShape(
            bottomStart = MediumRadius,
            bottomEnd = MediumRadius
        )
    }
    is ItemMainState.ItemListType.Middle -> {
        RectangleShape
    }
    is ItemMainState.ItemListType.Single -> {
        RoundedCornerShape(size = MediumRadius)
    }
}

fun List<ToDoGroup>.toItemGroup(selectedItemState: SelectedItemState): List<ItemMainState> {
    val data = mutableListOf<ItemMainState>()

    forEach {
        if (it.id != ToDoGroupDb.DEFAULT_ID) {
            data.add(ItemMainState.ItemGroup(it))
        }
        data.addAll(it.lists.toItemListMainState(selectedItemState))
    }

    return data
}

private fun List<ToDoList>.toItemListMainState(selectedItemState: SelectedItemState): List<ItemMainState.ItemListType> {
    return mapIndexed { index, list ->
        val selected = selectedItemState is SelectedItemState.List && selectedItemState.listId == list.id
        if (size == 1) {
            ItemMainState.ItemListType.Single(
                list = list,
                selected = selected
            )
        } else {
            when (index) {
                0 -> ItemMainState.ItemListType.First(
                    list = list,
                    selected = selected
                )
                lastIndex -> ItemMainState.ItemListType.Last(
                    list = list,
                    selected = selected
                )
                else -> ItemMainState.ItemListType.Middle(
                    list = list,
                    selected = selected
                )
            }
        }
    }
}

fun ToDoList.totalTask() = tasks.filter { it.status == ToDoStatus.IN_PROGRESS }.size
