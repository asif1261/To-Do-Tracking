package com.asifiqbal.todotracking.runtime

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import com.asifiqbal.todotracking.features.host.ui.Host
import com.asifiqbal.todotracking.foundation.window.WindowState
import com.asifiqbal.todotracking.foundation.window.rememberWindowState
import com.asifiqbal.todotracking.runtime.navigation.MainNavHost
import com.wisnu.kurniawan.composetodolist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var windowState: WindowState

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ToDoTracking_Light)
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            windowState = rememberWindowState()

            Host {
                Surface{
                    MainNavHost(windowState)
                }
            }
        }
    }
}