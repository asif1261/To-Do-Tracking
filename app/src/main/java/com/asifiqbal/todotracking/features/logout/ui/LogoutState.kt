package com.asifiqbal.todotracking.features.logout.ui

import androidx.compose.runtime.Immutable
import com.asifiqbal.todotracking.model.User

@Immutable
data class LogoutState(val user: User = User())
