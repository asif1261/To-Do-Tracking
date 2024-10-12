package com.asifiqbal.todotracking.foundation.datasource.preference.mapper

import com.asifiqbal.todotracking.model.Language
import com.wisnu.kurniawan.composetodolist.foundation.datasource.preference.model.LanguagePreference

fun Language.toLanguagePreference(): LanguagePreference {
    return when (this) {
        Language.ENGLISH -> LanguagePreference.ENGLISH
    }
}

fun LanguagePreference.toLanguage(): Language {
    return when (this) {
        LanguagePreference.ENGLISH -> Language.ENGLISH
    }
}
