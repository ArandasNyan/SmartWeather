package br.com.fiap.smartweather.domain.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDataDto(
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature: List<Float>,
    @SerialName("apparent_temperature")
    val apparentTemperature: List<Float>,
    @SerialName("weathercode")
    val weatherCode: List<Int>,
    @SerialName("precipitation_probability")
    val precipitationProbability: List<Int?>,
    @SerialName("windspeed_10m")
    val windSpeed: List<Float>,
)

@Serializable
data class HourlyWeatherUnitsDto(
    @SerialName("temperature_2m")
    val temperature: String,
    @SerialName("apparent_temperature")
    val apparentTemperature: String,
    @SerialName("precipitation_probability")
    val precipitationProbability: String,
    @SerialName("windspeed_10m")
    val windSpeed: String,
)