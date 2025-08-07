package com.example.myapplication.ui.shared.dataModel
import kotlinx.serialization.Serializable;

@Serializable
data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)

@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)

@Serializable
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

@Serializable
data class Clouds(
    val all: Int
)

@Serializable
data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

@Serializable
data class Account(
    var name: String,
    var middleLastName: String,
    var fullName: String,
    var email: String,
    var phone: String?,
    var age: Int,
    var createdAt: String,
    var updatedAt: String,
    var imageAvatar: String,
    var username: String,
    var password: String
)