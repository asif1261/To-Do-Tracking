package com.asifiqbal.todotracking.sensortracking

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SensorViewModel : ViewModel() {
    // StateFlows to track sensor data
    private val _gyroData = MutableStateFlow<List<Float>>(emptyList())
    val gyroData: StateFlow<List<Float>> = _gyroData

    private val _accelerometerData = MutableStateFlow<List<Float>>(emptyList())
    val accelerometerData: StateFlow<List<Float>> = _accelerometerData

    private val _alert = MutableStateFlow(false)
    val alert: StateFlow<Boolean> = _alert

    // Methods to update the sensor data and alert
    fun updateGyroData(newData: List<Float>) {
        _gyroData.update { newData }
    }

    fun updateAccelerometerData(newData: List<Float>) {
        _accelerometerData.update { newData }
    }

    fun triggerAlert(isAlert: Boolean) {
        _alert.update { isAlert }
    }
}
