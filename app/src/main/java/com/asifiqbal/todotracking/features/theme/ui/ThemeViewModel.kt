package com.asifiqbal.todotracking.features.theme.ui

import android.os.Build
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.asifiqbal.todotracking.features.theme.data.IThemeEnvironment
import com.asifiqbal.todotracking.foundation.theme.AuroraItemBackgroundL2
import com.asifiqbal.todotracking.foundation.theme.AuroraPrimary
import com.asifiqbal.todotracking.foundation.theme.LightPrimary
import com.asifiqbal.todotracking.foundation.theme.NightItemBackgroundL2
import com.asifiqbal.todotracking.foundation.theme.NightPrimary
import com.asifiqbal.todotracking.foundation.theme.SunriseItemBackgroundL2
import com.asifiqbal.todotracking.foundation.theme.SunrisePrimary
import com.asifiqbal.todotracking.foundation.theme.TwilightItemBackgroundL1
import com.asifiqbal.todotracking.foundation.theme.TwilightPrimary
import com.asifiqbal.todotracking.model.Theme
import com.wisnu.foundation.coreviewmodel.StatefulViewModel
import com.asifiqbal.todotracking.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    themeEnvironment: IThemeEnvironment,
) :
    StatefulViewModel<ThemeState, Unit, ThemeAction, IThemeEnvironment>(ThemeState(), themeEnvironment) {

    init {
        initTheme()
    }

    override fun dispatch(action: ThemeAction) {
        when (action) {
            is ThemeAction.SelectTheme -> applyTheme(action.selected)
        }
    }

    private fun initTheme() {
        viewModelScope.launch {
            setState { copy(items = initial()) }

            environment.getTheme()
                .collect {
                    setState { copy(items = items.select(it)) }
                }
        }
    }

    private fun applyTheme(item: ThemeItem) {
        viewModelScope.launch {
            environment.setTheme(item.theme)
        }
    }

    private fun initial(): List<ThemeItem> {
        val data = mutableListOf<ThemeItem>()

        data.add(
            ThemeItem(
                R.string.setting_theme_automatic,
                Theme.SYSTEM,
                Brush.linearGradient(
                    colors = listOf(
                        Color.White,
                        NightItemBackgroundL2
                    )
                ),
                false
            )
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            data.add(
                ThemeItem(
                    R.string.setting_theme_wallpaper,
                    Theme.WALLPAPER,
                    Brush.linearGradient(
                        colors = listOf()
                    ),
                    false
                )
            )
        }

        data.add(
            ThemeItem(
                R.string.setting_theme_light,
                Theme.LIGHT,
                Brush.linearGradient(
                    colors = listOf(
                        LightPrimary,
                        Color.White
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_twilight,
                Theme.TWILIGHT,
                Brush.linearGradient(
                    colors = listOf(
                        TwilightPrimary,
                        TwilightItemBackgroundL1
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_night,
                Theme.NIGHT,
                Brush.linearGradient(
                    colors = listOf(
                        NightPrimary,
                        NightItemBackgroundL2
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_sunrise,
                Theme.SUNRISE,
                Brush.linearGradient(
                    colors = listOf(
                        SunrisePrimary,
                        SunriseItemBackgroundL2
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_aurora,
                Theme.AURORA,
                Brush.linearGradient(
                    colors = listOf(
                        AuroraPrimary,
                        AuroraItemBackgroundL2
                    )
                ),
                false
            )
        )

        return data
    }

}

fun List<ThemeItem>.select(theme: Theme): List<ThemeItem> {
    return map {
        it.copy(applied = it.theme == theme)
    }
}
