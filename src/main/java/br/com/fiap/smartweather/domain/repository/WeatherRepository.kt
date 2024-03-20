package br.com.fiap.smartweather.domain.repository

import br.com.fiap.smartweather.app.screensmodels.UnitPreferenceManager
import br.com.fiap.smartweather.domain.mappers.toDailyWeatherData
import br.com.fiap.smartweather.domain.mappers.toHourlyWeatherInfo
import br.com.fiap.smartweather.domain.remote.WeatherApi
import br.com.fiap.smartweather.domain.remote.WeatherDto
import br.com.fiap.smartweather.domain.util.Resource
import br.com.fiap.smartweather.domain.weather.DailyWeatherData
import br.com.fiap.smartweather.domain.weather.HourlyWeatherInfo

class WeatherRepository(private val api: WeatherApi) {
    private suspend fun getWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true,
    ): WeatherDto {
        return if (cache) api.getWeatherData(
            lat,
            long,
            units.tempUnit.name.lowercase(),
            units.windUnit.name.lowercase(),
            units.precipitationUnit.name.lowercase(),
        ) else api.getWeatherDataWithoutCache(
            lat,
            long,
            units.tempUnit.name,
            units.windUnit.name,
            units.precipitationUnit.name,
        )
    }

    suspend fun getDailyWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true
    ): Resource<List<DailyWeatherData>> {
        return try {
            Resource.Success(
                with(getWeatherData(lat, long, units, cache)) {
                    dailyWeatherData.toDailyWeatherData(dailyUnits)
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    suspend fun getHourlyWeatherData(
        lat: Float,
        long: Float,
        units: UnitPreferenceManager,
        cache: Boolean = true
    ): Resource<HourlyWeatherInfo> {
        return try {
            Resource.Success(
                getWeatherData(lat, long, units, cache).toHourlyWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}