package br.com.fiap.smartweather.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.com.fiap.smartweather.app.screens.LocationPickerScreen
import br.com.fiap.smartweather.app.screens.MainScreen
import br.com.fiap.smartweather.app.screensmodels.AppearancePreferenceManager
import br.com.fiap.smartweather.app.screensmodels.LocationPreferenceManager
import br.com.fiap.smartweather.app.screensmodels.Theme
import br.com.fiap.smartweather.app.ui.theme.SmartWeatherTheme
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.koin.android.ext.android.inject

class WeatherActivity : ComponentActivity() {
    private val prefs: AppearancePreferenceManager by inject()
    private val location: LocationPreferenceManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDark = when (prefs.theme) {
                Theme.SYSTEM -> isSystemInDarkTheme()
                Theme.LIGHT -> false
                Theme.DARK -> true
            }

            SmartWeatherTheme(darkTheme = isDark, monet = prefs.monet) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigator(
                        screen = if (location.locations.isEmpty()) LocationPickerScreen() else MainScreen(),
                    ) {
                        SlideTransition(it)
                    }
                }
            }
        }
    }
}

