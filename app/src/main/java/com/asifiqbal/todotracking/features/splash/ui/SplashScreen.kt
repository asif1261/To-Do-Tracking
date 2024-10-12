package com.asifiqbal.todotracking.features.splash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.asifiqbal.todotracking.foundation.viewmodel.HandleEffect
import com.asifiqbal.todotracking.runtime.navigation.AuthFlow
import com.asifiqbal.todotracking.runtime.navigation.HomeFlow
import com.asifiqbal.todotracking.runtime.navigation.MainFlow

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
) {
    HandleEffect(viewModel) {
        when (it) {
            SplashEffect.NavigateToDashboard -> {
                navController.navigate(HomeFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
            SplashEffect.NavigateToLogin -> {
                navController.navigate(AuthFlow.Root.route) {
                    popUpTo(MainFlow.Root.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}
