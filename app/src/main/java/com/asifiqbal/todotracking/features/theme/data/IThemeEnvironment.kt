package com.asifiqbal.todotracking.features.theme.data

import com.asifiqbal.todotracking.model.Theme
import kotlinx.coroutines.flow.Flow

interface IThemeEnvironment {
    fun getTheme(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
}
