package com.asifiqbal.todotracking.features.todo.step.ui

sealed class StepEffect {
    data class ScrollTo(val position: Int) : StepEffect()
}
