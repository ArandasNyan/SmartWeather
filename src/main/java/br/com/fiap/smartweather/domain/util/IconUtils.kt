package br.com.fiap.smartweather.domain.util

import br.com.fiap.smartweather.domain.weather.DailyWeatherData
import br.com.fiap.smartweather.domain.weather.HourlyWeatherData

fun getIcon(
    data: HourlyWeatherData,
    dailyData: DailyWeatherData,
): Int {
    return if (data.time.isAfter(dailyData.sunrise) && data.time.isBefore(
            dailyData.sunset
        )
    ) data.weatherType.iconRes else data.weatherType.nightIconRes
}