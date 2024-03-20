package br.com.fiap.smartweather.domain.weather

data class HourlyWeatherInfo(
    val weatherData: List<HourlyWeatherData>,
    val currentWeatherData: HourlyWeatherData?,
)