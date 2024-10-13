package com.asifiqbal.todotracking.sensortracking

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@RequiresApi(Build.VERSION_CODES.M)
class SensorViewModel (
    application: Application
) : AndroidViewModel(application), SensorEventListener {

    @RequiresApi(Build.VERSION_CODES.M)
    private val sensorManager = application.getSystemService(SensorManager::class.java)

    // LiveData for Gyroscope and Accelerometer data
    private val _gyroData = MutableLiveData<List<Float>>()
    val gyroData: LiveData<List<Float>> = _gyroData

    private val _accelerometerData = MutableLiveData<List<Float>>()
    val accelerometerData: LiveData<List<Float>> = _accelerometerData

    private val _alert = MutableLiveData(false)
    val alert: LiveData<Boolean> = _alert

    init {
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (event.sensor.type) {
                Sensor.TYPE_GYROSCOPE -> {
                    _gyroData.value = event.values.toList()
                    checkForAlert()
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    _accelerometerData.value = event.values.toList()
                    checkForAlert()
                }
            }
        }
    }

    private fun checkForAlert() {
        // Trigger alert if high movement is detected on any two axes simultaneously
        val gyroValues = gyroData.value ?: return
        val accelerometerValues = accelerometerData.value ?: return

        val isHighGyro = gyroValues.any { Math.abs(it) > 5 } // Example threshold
        val isHighAccelerometer = accelerometerValues.any { Math.abs(it) > 5 }

        if (isHighGyro && isHighAccelerometer) {
            _alert.value = true
        } else {
            _alert.value = false
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Nothing to do now
    }

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}