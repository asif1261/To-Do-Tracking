package com.asifiqbal.todotracking.features.theme.ui

sealed class ThemeAction {
    data class SelectTheme(val selected: ThemeItem) : ThemeAction()
}
