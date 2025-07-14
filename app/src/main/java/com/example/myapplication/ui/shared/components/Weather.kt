package com.example.myapplication.ui.shared.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.shared.viewModel.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Weather(){
    var _weatherData: HttpResponse? by remember { mutableStateOf<HttpResponse?>(null) }
    val context = LocalContext.current
    var textContent by remember { mutableStateOf<String>("") }
    val weather: WeatherViewModel = viewModel();
    val scope = rememberCoroutineScope();

    // Synchronous read (risky on main thread)
    try {
        val inputStream = context.assets.open("openweather.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            stringBuilder.append(line).append("\n")
            line = reader.readLine()
        }
        reader.close()
        textContent = stringBuilder.toString()
    } catch (e: Exception) {
        textContent = "Error reading file: ${e.message}"
    }

    val key = textContent.split("=").get(1);


    println("textContent "+key);

    scope.launch {
        weather.fetchWeater(city = "Hanoi", key=key);
    }
    Column {
        Text("This is weather component")
    }
}