package com.wisnu.kurniawan.composetodolist.foundation.preview

import com.asifiqbal.todotracking.foundation.wrapper.DateTimeProviderImpl
import com.asifiqbal.todotracking.model.ToDoColor
import com.asifiqbal.todotracking.model.ToDoGroup
import com.asifiqbal.todotracking.model.ToDoList
import com.asifiqbal.todotracking.model.ToDoStatus
import com.asifiqbal.todotracking.model.ToDoStep
import com.asifiqbal.todotracking.model.ToDoTask

fun dummyRandomData() = buildGroup(
    muchGroup = 5,
    muchList = 10,
    muchTask = 10,
    muchStep = 5
)

fun dummySmallData() = buildGroup(
    muchGroup = 10,
    muchList = 1,
    muchTask = 1,
    muchStep = 1
)

fun dummyEmptyData() = buildGroup(
    muchGroup = 0,
    muchList = 0,
    muchTask = 0,
    muchStep = 0
)

private fun buildGroup(
    muchGroup: Int,
    muchList: Int,
    muchTask: Int,
    muchStep: Int
): List<ToDoGroup> {
    return build(muchGroup) {
        ToDoGroup(
            id = "group$it",
            name = "Group$it",
            lists = buildList(muchList, muchTask, muchStep),
            createdAt = DateTimeProviderImpl().now(),
            updatedAt = DateTimeProviderImpl().now()
        )
    }
}

private fun buildList(
    muchList: Int,
    muchTask: Int,
    muchStep: Int
): List<ToDoList> {
    return build(muchList) {
        ToDoList(
            id = "list$it",
            name = "List$it",
            color = ToDoColor.BLUE,
            tasks = buildTask(muchTask, muchStep),
            createdAt = DateTimeProviderImpl().now(),
            updatedAt = DateTimeProviderImpl().now()
        )
    }
}

private fun buildTask(
    muchTask: Int,
    muchStep: Int
): List<ToDoTask> {
    return build(muchTask) {
        ToDoTask(
            id = "task$it",
            name = "Task$it",
            status = ToDoStatus.IN_PROGRESS,
            steps = buildStep(muchStep),
            createdAt = DateTimeProviderImpl().now(),
            updatedAt = DateTimeProviderImpl().now()
        )
    }
}

private fun buildStep(muchStep: Int): List<ToDoStep> {
    return build(muchStep) {
        ToDoStep(
            id = "step$it",
            name = "Step$it",
            status = ToDoStatus.IN_PROGRESS,
            createdAt = DateTimeProviderImpl().now(),
            updatedAt = DateTimeProviderImpl().now()
        )
    }
}

private fun <T> build(much: Int, obj: (Int) -> T): List<T> {
    val list = mutableListOf<T>()
    for (i in 0 until much) {
        list.add(obj(i))
    }
    return list
}
