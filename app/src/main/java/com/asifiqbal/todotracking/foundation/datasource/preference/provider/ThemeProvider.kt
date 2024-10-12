package com.asifiqbal.todotracking.foundation.datasource.preference.provider

import androidx.datastore.core.DataStore
import com.asifiqbal.todotracking.foundation.datasource.preference.mapper.toTheme
import com.asifiqbal.todotracking.foundation.datasource.preference.mapper.toThemePreference
import com.asifiqbal.todotracking.foundation.datasource.preference.model.ThemePreference
import com.asifiqbal.todotracking.foundation.di.DiName
import com.asifiqbal.todotracking.model.Theme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ThemeProvider @Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val themeDataStore: DataStore<ThemePreference>,
) {

    fun getTheme(): Flow<Theme> {
        return themeDataStore.data.map { it.toTheme() }
            .catch { emit(Theme.SYSTEM) }
            .flowOn(dispatcher)
    }

    suspend fun setTheme(data: Theme) {
        withContext(dispatcher) {
            themeDataStore.updateData {
                data.toThemePreference()
            }
        }
    }

}
