package br.com.fiap.smartweather.app.screensmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.fiap.smartweather.domain.repository.WeatherRepository
import br.com.fiap.smartweather.domain.util.Resource
import br.com.fiap.smartweather.domain.weather.DailyWeatherData
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch

data class DailyWeatherState(
    val dailyWeatherData: List<DailyWeatherData>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val expanded: Int? = null
)

class DailyWeatherScreenModel(
    private val repository: WeatherRepository,
    private val unitsPreferenceManager: UnitPreferenceManager,
    val locationPreferenceManager: LocationPreferenceManager,
) : ScreenModel {
    var state by mutableStateOf(DailyWeatherState())
        private set

    fun loadWeatherInfo(cache: Boolean = true) {
        val location = locationPreferenceManager.locations[locationPreferenceManager.selectedIndex]
        screenModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            state = when (val result = repository.getDailyWeatherData(
                lat = location.latitude,
                long = location.longitude,
                cache = cache,
                units = unitsPreferenceManager,
            )) {
                is Resource.Success -> {
                    state.copy(
                        dailyWeatherData = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state.copy(
                        dailyWeatherData = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    fun setExpanded(index: Int?) {
        state = state.copy(expanded = index)
    }
}