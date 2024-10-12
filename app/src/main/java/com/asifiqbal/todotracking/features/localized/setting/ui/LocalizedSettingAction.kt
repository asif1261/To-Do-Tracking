package com.asifiqbal.todotracking.features.localized.setting.ui

sealed class LocalizedSettingAction {
    data class SelectLanguage(val selected: LanguageItem) : LocalizedSettingAction()
}
