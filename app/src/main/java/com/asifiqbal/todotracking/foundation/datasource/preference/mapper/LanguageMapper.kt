package com.asifiqbal.todotracking.foundation.datasource.preference.mapper

import com.asifiqbal.todotracking.foundation.datasource.preference.model.LanguagePreference
import com.asifiqbal.todotracking.model.Language

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
