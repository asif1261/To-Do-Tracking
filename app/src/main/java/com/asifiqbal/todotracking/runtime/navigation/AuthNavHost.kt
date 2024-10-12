package com.asifiqbal.todotracking.runtime.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.asifiqbal.todotracking.features.login.ui.LoginScreen
import com.asifiqbal.todotracking.features.login.ui.LoginViewModel

fun NavGraphBuilder.AuthNavHost(navController: NavHostController) {
    navigation(
        startDestination = AuthFlow.LoginScreen.route,
        route = AuthFlow.Root.route
    ) {
        composable(route = AuthFlow.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                viewModel = viewModel,
                onNavigateToDashboard = {
                    navController.navigate(HomeFlow.Root.route) {
                        popUpTo(AuthFlow.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
