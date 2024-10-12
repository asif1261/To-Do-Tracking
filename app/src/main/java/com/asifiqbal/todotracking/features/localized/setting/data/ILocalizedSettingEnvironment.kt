package com.asifiqbal.todotracking.features.localized.setting.data

import com.asifiqbal.todotracking.model.Language
import kotlinx.coroutines.flow.Flow

interface ILocalizedSettingEnvironment {
    fun getLanguage(): Flow<Language>
    suspend fun setLanguage(language: Language)
}

