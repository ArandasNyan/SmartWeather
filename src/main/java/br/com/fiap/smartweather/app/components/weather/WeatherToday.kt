package br.com.fiap.smartweather.app.components.weather

import br.com.fiap.smartweather.app.screensmodels.LocationPreferenceManager
import br.com.fiap.smartweather.domain.weather.DailyWeatherData

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import br.com.fiap.smartweather.R

@Composable
fun WeatherToday(data: DailyWeatherData) {
    val locationPreferenceManager: LocationPreferenceManager = koinInject()

    Card(
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.today_in, with(locationPreferenceManager) {
                    locations.getOrNull(selectedIndex)?.location?.split(",")?.first()
                        ?: stringResource(id = R.string.unknown)
                }),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                WeatherDataDisplay(
                    value = data.temperatureMax,
                    unit = data.units.temperatureMax,
                    icon = Icons.Default.ArrowUpward,
                    description = stringResource(R.string.weather_high, data.temperatureMax, data.units.temperatureMax)
                )
                WeatherDataDisplay(
                    value = data.temperatureMin,
                    unit = data.units.temperatureMin,
                    icon = Icons.Default.ArrowDownward,
                    description = stringResource(id = R.string.weather_low, data.temperatureMin, data.units.temperatureMin)
                )
                WeatherDataDisplay(
                    value = data.precipitationProbabilityMax,
                    unit = data.units.precipitationProbabilityMax,
                    icon = Icons.Outlined.WaterDrop,
                    description = data.precipitationProbabilityMax?.let {
                        stringResource(
                            id = R.string.weather_precipitation,
                            it
                        )
                    } ?: stringResource(id = R.string.unknown)
                )
            }
        }
    }
}