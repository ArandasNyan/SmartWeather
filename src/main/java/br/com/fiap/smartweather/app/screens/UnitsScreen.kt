package br.com.fiap.smartweather.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import br.com.fiap.smartweather.R
import br.com.fiap.smartweather.app.components.navigation.SmallToolbar
import br.com.fiap.smartweather.app.components.settings.SettingsItemChoice
import br.com.fiap.smartweather.app.screensmodels.UnitPreferencesScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

class UnitsScreen : Screen {
    @Composable
    override fun Content(){
        val screenModel: UnitPreferencesScreenModel = getScreenModel()
        val context = LocalContext.current

        Scaffold(topBar = {
            SmallToolbar(
                title = { Text(text = stringResource(R.string.settings_units)) },
                backButton = true
            )
        }) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                SettingsItemChoice(
                    label = stringResource(R.string.unit_temp),
                    secondaryLabel = stringResource(R.string.unit_temp_desc),
                    pref = screenModel.prefs.tempUnit,
                    labelFactory = { context.getString(it.label) }
                ) { screenModel.prefs.tempUnit = it }

                SettingsItemChoice(
                    label = stringResource(R.string.unit_wind),
                    secondaryLabel = stringResource(R.string.unit_wind_desc),
                    pref = screenModel.prefs.windUnit,
                    labelFactory = { context.getString(it.label) }
                ) { screenModel.prefs.windUnit = it }

                SettingsItemChoice(
                    label = stringResource(R.string.unit_precipitation),
                    secondaryLabel = stringResource(R.string.unit_precipitation_desc),
                    pref = screenModel.prefs.precipitationUnit,
                    labelFactory = { context.getString(it.label) }
                ) { screenModel.prefs.precipitationUnit = it }
            }
        }
    }
}