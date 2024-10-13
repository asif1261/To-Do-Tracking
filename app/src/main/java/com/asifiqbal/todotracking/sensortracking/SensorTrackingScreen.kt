package com.asifiqbal.todotracking.sensortracking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun SensorTrackingApp(viewModel: SensorViewModel) {

    val gyroData by viewModel.gyroData.collectAsState()
    val accelerometerData by viewModel.accelerometerData.collectAsState()
    val alert by viewModel.alert.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Gyroscope Data", style = MaterialTheme.typography.h6)
        GraphDisplay(data = gyroData)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Accelerometer Data", style = MaterialTheme.typography.h6)
        GraphDisplay(data = accelerometerData)

        Spacer(modifier = Modifier.height(16.dp))

        if (alert) {
            Text(
                text = "ALERT",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Composable
fun GraphDisplay(data: List<Float>) {
    val context = LocalContext.current
    val chart = rememberLineChart(context)

    chart.data = LineData(LineDataSet(data.mapIndexed { index, value ->
        Entry(index.toFloat(), value)
    }, "Sensor Data"))

    AndroidView(
        factory = { chart },
        modifier = Modifier.fillMaxWidth().height(200.dp)
    )
}

@Composable
fun rememberLineChart(
    context: android.content.Context
): LineChart {
    return remember {
        val chart = LineChart(context)
        chart.description = Description().apply { text = "" }
        chart
    }
}
