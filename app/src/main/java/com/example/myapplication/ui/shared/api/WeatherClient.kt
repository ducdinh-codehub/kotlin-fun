package com.example.myapplication.ui.shared.api
import com.example.myapplication.ui.shared.dataModel.WeatherResponse
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*

object WeatherClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                coerceInputValues = true // Helps with optional fields
            })
        }
    }

    suspend fun getWeather(city: String = "", apiKey: String = "", lot: Number = 0, lat: Number = 0): WeatherResponse? {
        return try {
            val rst: WeatherResponse = client.get("https://api.openweathermap.org/data/2.5/weather") {
                url {
                    parameters.append("q", city)
                    parameters.append("appid", apiKey)
                }
            }.body();
            return rst;
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}