package com.asifiqbal.todotracking.features.localized.setting.data

import com.asifiqbal.todotracking.foundation.datasource.preference.provider.LanguageProvider
import com.asifiqbal.todotracking.model.Language
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalizedSettingEnvironment @Inject constructor(
    private val languageProvider: LanguageProvider
) : ILocalizedSettingEnvironment {

    override fun getLanguage(): Flow<Language> {
        return languageProvider.getLanguage()
    }

    override suspend fun setLanguage(language: Language) {
        languageProvider.setLanguage(language)
    }

}
