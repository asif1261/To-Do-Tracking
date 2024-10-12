package com.asifiqbal.todotracking.foundation.extension

import androidx.compose.ui.graphics.Color
import com.asifiqbal.todotracking.foundation.theme.ListBlue
import com.asifiqbal.todotracking.foundation.theme.ListBrown
import com.asifiqbal.todotracking.foundation.theme.ListGreen
import com.asifiqbal.todotracking.foundation.theme.ListOrange
import com.asifiqbal.todotracking.foundation.theme.ListPurple
import com.asifiqbal.todotracking.foundation.theme.ListRed
import com.asifiqbal.todotracking.foundation.theme.ListYellow
import com.asifiqbal.todotracking.model.ToDoColor

fun ToDoColor.toColor() = when (this) {
    ToDoColor.BLUE -> ListBlue
    ToDoColor.RED -> ListRed
    ToDoColor.GREEN -> ListGreen
    ToDoColor.YELLOW -> ListYellow
    ToDoColor.ORANGE -> ListOrange
    ToDoColor.PURPLE -> ListPurple
    ToDoColor.BROWN -> ListBrown
}

fun Color.toToDoColor() = when (this) {
    ListRed -> ToDoColor.RED
    ListGreen -> ToDoColor.GREEN
    ListYellow -> ToDoColor.YELLOW
    ListOrange -> ToDoColor.ORANGE
    ListPurple -> ToDoColor.PURPLE
    ListBrown -> ToDoColor.BROWN
    else -> ToDoColor.BLUE
}

