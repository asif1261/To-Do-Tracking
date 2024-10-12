package com.asifiqbal.todotracking.features.localized.setting.ui

import com.asifiqbal.todotracking.model.Language

sealed class LocalizedEffect {
    data class ApplyLanguage(val language: Language) : LocalizedEffect()
}
