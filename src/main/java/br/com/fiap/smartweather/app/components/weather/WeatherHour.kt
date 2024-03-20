package br.com.fiap.smartweather.app.components.weather

import br.com.fiap.smartweather.domain.util.getIcon
import br.com.fiap.smartweather.domain.weather.DailyWeatherData
import br.com.fiap.smartweather.domain.weather.HourlyWeatherData

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHour(
    data: HourlyWeatherData,
    dailyData: DailyWeatherData,
    modifier: Modifier = Modifier,
    onChangeSelected: () -> Unit
) {
    data.let {
        val formattedTime by remember {
            derivedStateOf { it.time.format(DateTimeFormatter.ofPattern("HH:mm")) }
        }
        Card(modifier = modifier.clickable {
            onChangeSelected()
        }) {
            Column(
                modifier = Modifier
                    .height(128.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(text = formattedTime)
                Image(
                    painter = painterResource(
                        id = getIcon(it, dailyData)
                    ),
                    contentDescription = it.weatherType.weatherDesc,
                    modifier = Modifier.width(40.dp)
                )
                Text(text = "${it.temperature}${it.units.temperature}")
            }
        }
    }

}