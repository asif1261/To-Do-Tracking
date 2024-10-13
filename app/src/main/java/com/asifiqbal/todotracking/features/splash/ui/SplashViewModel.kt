package com.asifiqbal.todotracking.features.splash.ui

import androidx.lifecycle.viewModelScope
import com.asifiqbal.todotracking.features.splash.data.ISplashEnvironment
import com.wisnu.foundation.coreviewmodel.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    splashEnvironment: ISplashEnvironment
) : StatefulViewModel<Unit, SplashEffect, SplashAction, ISplashEnvironment>(
    Unit,
    splashEnvironment
) {

    init {
        dispatch(SplashAction.AppLaunch)
    }

    override fun dispatch(action: SplashAction) {
        when (action) {
            is SplashAction.AppLaunch -> {
                viewModelScope.launch {
                    environment.getCredential()
                        .collect {
                            setEffect(SplashEffect.NavigateToDashboard)
                        }
                }
            }
        }
    }
}

