package com.example.myapplication.ui.shared.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.ui.shared.api.WeatherClient
import com.example.myapplication.ui.shared.dataModel.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class WeatherViewModel: ViewModel() {
    private val _weather = MutableStateFlow<WeatherResponse?>(null);
    var weather = _weather.asStateFlow();

    private val _error = MutableStateFlow<String?>(null);
    var error = _error.asStateFlow();

    private val _isLoading = MutableStateFlow<Boolean>(false);
    var isLoadingather = _isLoading.asStateFlow();

    suspend fun fetchWeater(city: String = "", key: String = "", lat: Number = 0, lot: Number = 0){
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val weather = WeatherClient.getWeather(city, key, lat, lot);

                println("weather"+weather)
                _weather.value = weather
            } catch (e: Exception) {
                _error.value = "Failed to fetch weather: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}