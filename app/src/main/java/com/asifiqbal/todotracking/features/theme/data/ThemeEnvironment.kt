package com.asifiqbal.todotracking.features.theme.data

import com.asifiqbal.todotracking.foundation.datasource.preference.provider.ThemeProvider
import com.asifiqbal.todotracking.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeEnvironment @Inject constructor(
    private val themeProvider: ThemeProvider
) : IThemeEnvironment {

    override fun getTheme(): Flow<Theme> {
        return themeProvider.getTheme()
    }

    override suspend fun setTheme(theme: Theme) {
        themeProvider.setTheme(theme)
    }

}
