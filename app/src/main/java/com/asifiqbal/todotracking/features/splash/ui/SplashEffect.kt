package com.asifiqbal.todotracking.features.splash.ui

sealed class SplashEffect {
    object NavigateToDashboard : SplashEffect()
    object NavigateToLogin : SplashEffect()
}
