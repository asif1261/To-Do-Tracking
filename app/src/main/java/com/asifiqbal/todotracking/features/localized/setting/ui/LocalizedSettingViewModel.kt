package com.asifiqbal.todotracking.features.localized.setting.ui

import androidx.lifecycle.viewModelScope
import com.asifiqbal.todotracking.features.localized.setting.data.ILocalizedSettingEnvironment
import com.asifiqbal.todotracking.model.Language
import com.wisnu.foundation.coreviewmodel.StatefulViewModel
import com.wisnu.kurniawan.composetodolist.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalizedSettingViewModel @Inject constructor(localizedSettingEnvironment: ILocalizedSettingEnvironment) :
    StatefulViewModel<LocalizedSettingState, LocalizedEffect, LocalizedSettingAction, ILocalizedSettingEnvironment>(
        LocalizedSettingState(), localizedSettingEnvironment) {

    init {
        initLanguage()
    }

    override fun dispatch(action: LocalizedSettingAction) {
        when (action) {
            is LocalizedSettingAction.SelectLanguage -> {
                viewModelScope.launch {
                    environment.setLanguage(action.selected.language)
                    setEffect(LocalizedEffect.ApplyLanguage(action.selected.language))
                }
            }
        }
    }

    private fun initLanguage() {
        viewModelScope.launch {
            delay(100)
            environment.getLanguage()
                .collect {
                    setState { copy(items = initial().select(it)) }
                }
        }
    }

    private fun initial() = listOf(
        LanguageItem(
            title = R.string.setting_language_english,
            language = Language.ENGLISH,
            applied = false
        )
    )

}

fun List<LanguageItem>.select(language: Language): List<LanguageItem> {
    return map {
        it.copy(applied = it.language == language)
    }
}
