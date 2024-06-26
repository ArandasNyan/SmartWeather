package br.com.fiap.smartweather.app.components.weather


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.smartweather.app.screensmodels.HourlyWeatherState
import br.com.fiap.smartweather.domain.weather.DailyWeatherData
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: HourlyWeatherState,
    dailyData: DailyWeatherData,
    modifier: Modifier = Modifier,
    onChangeSelected: (Int) -> Unit
) {
    state.hourlyWeatherInfo?.weatherData?.let {
        LazyRow(modifier = modifier) {
            items(it.subList(LocalDateTime.now().hour, it.size)) {
                WeatherHour(
                    data = it,
                    dailyData = dailyData,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) { onChangeSelected(it.time.hour) }
            }
        }

    }
}