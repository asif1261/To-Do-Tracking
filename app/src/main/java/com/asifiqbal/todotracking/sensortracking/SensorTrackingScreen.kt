package com.asifiqbal.todotracking.sensortracking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun SensorTrackingApp(viewModel: SensorViewModel) {
    /*val gyroData by viewModel.gyroData.observeAsState(emptyList())
    val accelerometerData by viewModel.accelerometerData.observeAsState(emptyList())
    val alert by viewModel.alert.observeAsState(false)*/
}