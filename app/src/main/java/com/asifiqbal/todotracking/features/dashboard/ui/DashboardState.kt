package com.asifiqbal.todotracking.features.dashboard.ui

import androidx.compose.runtime.Immutable
import com.asifiqbal.todotracking.model.User

@Immutable
data class DashboardState(val user: User = User())
