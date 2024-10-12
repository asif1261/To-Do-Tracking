package com.asifiqbal.todotracking.features.todo.taskreminder.data

import com.asifiqbal.todotracking.foundation.datasource.local.provider.ToDoTaskProvider
import com.asifiqbal.todotracking.foundation.extension.getNextScheduledDueDate
import com.asifiqbal.todotracking.foundation.extension.toggleStatusHandler
import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProvider
import com.asifiqbal.todotracking.model.TaskWithList
import com.asifiqbal.todotracking.model.ToDoStatus
import com.asifiqbal.todotracking.model.ToDoTask
import com.wisnu.foundation.coreloggr.Loggr
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class TaskReminderEnvironment @Inject constructor(
    private val dateTimeProvider: DateTimeProvider,
    private val toDoTaskProvider: ToDoTaskProvider,
    private val alarmManager: TaskAlarmManager,
    private val notificationManager: TaskNotificationManager
) : ITaskReminderEnvironment {

    override fun notifyNotification(taskId: String): Flow<TaskWithList> {
        return getTask(taskId)
            .onEach { (list, task) ->
                Loggr.debug("AlarmFlow") { "AlarmShow $task $list" }
                notificationManager.show(task, list)
            }
    }

    override fun snoozeReminder(taskId: String): Flow<TaskWithList> {
        return getTask(taskId)
            .onEach { task ->
                alarmManager.scheduleTaskAlarm(task.task, dateTimeProvider.now().plusMinutes(15))
                notificationManager.dismiss(task.task)
            }
    }

    override suspend fun completeReminder(taskId: String): Flow<TaskWithList> {
        return getTask(taskId)
            .onEach { task ->
                val currentDate = dateTimeProvider.now()
                task.task.toggleStatusHandler(
                    currentDate,
                    { completedAt, newStatus ->
                        toDoTaskProvider.updateTaskStatus(task.task.id, newStatus, completedAt, currentDate)
                    },
                    { nextDueDate ->
                        toDoTaskProvider.updateTaskDueDate(task.task.id, nextDueDate, task.task.isDueDateTimeSet, currentDate)
                    }
                )

                alarmManager.cancelTaskAlarm(task.task)
                notificationManager.dismiss(task.task)
            }
    }

    override fun restartAllReminder(): Flow<List<ToDoTask>> {
        return toDoTaskProvider.getScheduledTasks()
            .take(1)
            .onEach { tasks ->
                tasks.forEach {
                    alarmManager.scheduleTaskAlarm(it, it.getNextScheduledDueDate(dateTimeProvider.now()))
                }
            }
    }

    private fun getTask(taskId: String): Flow<TaskWithList> {
        return toDoTaskProvider.getTaskWithListById(taskId)
            .take(1)
            .filter { task ->
                task.task.status != ToDoStatus.COMPLETE &&
                    task.task.dueDate != null
            }
    }

}
