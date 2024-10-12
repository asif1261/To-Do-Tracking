package com.asifiqbal.todotracking.features.todo.grouplist.ui

import com.asifiqbal.todotracking.model.GroupIdWithList

sealed class UpdateGroupListAction {

    data class Change(val item: GroupIdWithList) : UpdateGroupListAction()
    object Submit : UpdateGroupListAction()

}
