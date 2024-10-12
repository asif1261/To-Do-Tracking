package com.asifiqbal.todotracking.features.host.ui

import com.asifiqbal.todotracking.model.Theme
import javax.annotation.concurrent.Immutable

@Immutable
data class HostState(val theme: Theme = Theme.SYSTEM)
