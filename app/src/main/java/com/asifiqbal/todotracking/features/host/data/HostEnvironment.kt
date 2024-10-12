package com.asifiqbal.todotracking.features.host.data

import com.asifiqbal.todotracking.foundation.datasource.preference.provider.ThemeProvider
import com.asifiqbal.todotracking.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HostEnvironment @Inject constructor(
    private val themeProvider: ThemeProvider
) : IHostEnvironment {

    override fun getTheme(): Flow<Theme> {
        return themeProvider.getTheme()
    }

}
