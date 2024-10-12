package com.asifiqbal.todotracking.features.todo.detail.data

import com.asifiqbal.todotracking.foundation.datasource.local.entities.ToDoGroupDb
import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoListProvider
import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoTaskProvider
import com.asifiqbal.todotracking.foundation.extension.OnResolveDuplicateName
import com.asifiqbal.todotracking.foundation.extension.duplicateNameResolver
import com.asifiqbal.todotracking.foundation.extension.resolveListName
import com.asifiqbal.todotracking.foundation.extension.toggleStatusHandler
import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.foundation.wrapper.IdProvider
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoTask
import com.google.firebase.analytics.FirebaseAnalytics
import com.wisnu.foundation.libanalyticsmanager.AnalyticsManager
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@FlowPreview
class ListDetailEnvironment @Inject constructor(
    private val toDoListProvider: ToDoListProvider,
    private val toDoTaskProvider: ToDoTaskProvider,
    override val idProvider: IdProvider,
    override val dateTimeProvider: DateTimeProvider,
    private val analyticManager: AnalyticsManager
) : IListDetailEnvironment {

    override fun getListWithTasksById(listId: String): Flow<ToDoList> {
        return toDoListProvider.getListWithTasksById(listId)
    }

    override suspend fun createList(list: ToDoList): Flow<ToDoList> {
        val process: OnResolveDuplicateName = { newName ->
            toDoListProvider.insertList(
                listOf(list.copy(name = newName)),
                ToDoGroupDb.DEFAULT_ID
            )
        }

        return duplicateNameResolver(
            updateName = { process(list.name) },
            onDuplicate = { resolveListName(list.name, toDoListProvider.getList(), process) }
        )
            .flatMapConcat { toDoListProvider.getListWithTasksById(list.id) }
    }

    override suspend fun updateList(list: ToDoList): Flow<Any> {
        val currentDate = dateTimeProvider.now()
        val process: OnResolveDuplicateName = { newName ->
            toDoListProvider.updateListNameAndColor(list.copy(name = newName), currentDate)
        }

        return duplicateNameResolver(
            updateName = { process(list.name) },
            onDuplicate = { resolveListName(list.name, toDoListProvider.getList(), process) }
        )
    }

    override suspend fun createTask(taskName: String, listId: String) {
        val currentDate = dateTimeProvider.now()

        toDoTaskProvider.insertTask(
            listOf(
                ToDoTask(
                    id = idProvider.generate(),
                    name = taskName,
                    createdAt = currentDate,
                    updatedAt = currentDate
                )
            ),
            listId
        )
    }

    override suspend fun toggleTaskStatus(toDoTask: ToDoTask) {
        val currentDate = dateTimeProvider.now()
        toDoTask.toggleStatusHandler(
            currentDate,
            { completedAt, newStatus ->
                toDoTaskProvider.updateTaskStatus(toDoTask.id, newStatus, completedAt, currentDate)
            },
            { nextDueDate ->
                toDoTaskProvider.updateTaskDueDate(toDoTask.id, nextDueDate, toDoTask.isDueDateTimeSet, currentDate)
            }
        )
    }

    override suspend fun deleteTask(task: ToDoTask) {
        toDoTaskProvider.deleteTaskById(task.id)
    }

    override fun trackSaveListButtonClicked() {
        analyticManager.trackEvent(
            FirebaseAnalytics.Event.SELECT_CONTENT,
            mapOf(
                FirebaseAnalytics.Param.SCREEN_NAME to "list_detail",
                FirebaseAnalytics.Param.ITEM_NAME to "button_save_list",
            ),
        )
    }

}
