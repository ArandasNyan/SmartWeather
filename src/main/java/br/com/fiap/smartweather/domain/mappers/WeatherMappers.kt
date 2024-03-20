package br.com.fiap.smartweather.domain.mappers


import br.com.fiap.smartweather.domain.remote.DailyWeatherDataDto
import br.com.fiap.smartweather.domain.remote.DailyWeatherUnitsDto
import br.com.fiap.smartweather.domain.remote.HourlyWeatherDataDto
import br.com.fiap.smartweather.domain.remote.HourlyWeatherUnitsDto
import br.com.fiap.smartweather.domain.remote.WeatherDto
import br.com.fiap.smartweather.domain.weather.DailyWeatherData
import br.com.fiap.smartweather.domain.weather.HourlyWeatherData
import br.com.fiap.smartweather.domain.weather.HourlyWeatherInfo
import br.com.fiap.smartweather.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun HourlyWeatherDataDto.toHourlyWeatherData(units: HourlyWeatherUnitsDto): List<HourlyWeatherData> {
    return time.subList(0, 24).mapIndexed { index, time ->
        HourlyWeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperature = temperature[index].roundToInt(),
            apparentTemperature = apparentTemperature[index].roundToInt(),
            windSpeed = windSpeed[index].roundToInt(),
            precipitationProbability = precipitationProbability.getOrNull(index),
            weatherType = WeatherType.fromWMO(weatherCode[index]),
            units = units.copy(
                windSpeed = units.windSpeed.replace("mp/h", "mph"),
            )
        )
    }
}

fun DailyWeatherDataDto.toDailyWeatherData(units: DailyWeatherUnitsDto): List<DailyWeatherData> {
    return date.mapIndexed { index, date ->
        DailyWeatherData(
            date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE),
            weatherType = WeatherType.fromWMO(weatherCode[index]),
            apparentTemperatureMax = apparentTemperatureMax[index].roundToInt(),
            apparentTemperatureMin = apparentTemperatureMin[index].roundToInt(),
            temperatureMax = temperatureMax[index].roundToInt(),
            temperatureMin = temperatureMin[index].roundToInt(),
            precipitationProbabilityMax = precipitationProbabilityMax.getOrNull(index),
            windSpeedMax = windSpeedMax[index].roundToInt(),
            sunrise = LocalDateTime.parse(sunrise[index]),
            sunset = LocalDateTime.parse(sunset[index]),
            precipitationSum = precipitationSum[index],
            units = units.copy(
                precipitationSum = units.precipitationSum.replace("inch", "\""),
                windSpeedMax = units.windSpeedMax.replace("mp/h", "mph"),
            )
        )
    }
}

fun WeatherDto.toHourlyWeatherInfo(): HourlyWeatherInfo {
    val weatherDataMap = hourlyWeatherData.toHourlyWeatherData(units = hourlyUnits)
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap.find {
        it.time.hour == now.hour
    }
    return HourlyWeatherInfo(
        weatherData = weatherDataMap,
        currentWeatherData = currentWeatherData,
    )
}