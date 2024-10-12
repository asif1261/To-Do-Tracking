package com.asifiqbal.todotracking.features.host.data

import com.asifiqbal.todotracking.model.Theme
import kotlinx.coroutines.flow.Flow

interface IHostEnvironment {
    fun getTheme(): Flow<Theme>
}
