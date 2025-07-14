package com.example.myapplication.ui.shared.api
import com.example.myapplication.ui.shared.dataModel.WeatherResponse
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.request.get

object WeatherClient {
    private val client = HttpClient(CIO);
    suspend fun getWeather(city: String = "", apiKey: String = "", lot: Number = 0, lat: Number = 0): WeatherResponse? {
        return try {
            client.get("https://api.openweathermap.org/data/2.5/weather") {
                url {
                    parameters.append("q", city)
                    parameters.append("appid", apiKey)
                }
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}