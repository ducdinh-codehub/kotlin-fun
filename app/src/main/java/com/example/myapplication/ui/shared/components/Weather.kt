package com.example.myapplication.ui.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


suspend fun callWeatherAPI(client: HttpClient): HttpResponse {
    val response: HttpResponse = client.get("https://ktor.io/")
    return response;
}
@Composable
fun Weather(){
    val client = HttpClient(CIO)
    var _weatherData = MutableSharedFlow<HttpResponse>();
    var weatherData = _weatherData.asSharedFlow();

    println("weatherData"+weatherData);
    LaunchedEffect(client) {
        _weatherData.emit(callWeatherAPI(client));
    }

    Column {
        Text("This is weather component")
    }
}