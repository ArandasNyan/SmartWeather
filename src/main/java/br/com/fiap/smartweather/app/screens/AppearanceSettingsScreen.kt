package br.com.fiap.smartweather.app.screens

import br.com.fiap.smartweather.app.screensmodels.AppearancePreferencesScreenModel

import android.os.Build
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import br.com.fiap.smartweather.app.components.navigation.SmallToolbar
import br.com.fiap.smartweather.app.components.settings.SettingsItemChoice
import br.com.fiap.smartweather.app.components.settings.SettingsSwitch

class AppearanceSettingsScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: AppearancePreferencesScreenModel = getScreenModel()
        val context = LocalContext.current

        Scaffold(topBar = {
            SmallToolbar(
                title = { Text(text = stringResource(R.string.settings_appearance)) },
                backButton = true
            )
        }) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {

                SettingsSwitch(
                    label = stringResource(R.string.appearance_monet),
                    secondaryLabel = stringResource(R.string.appearance_monet_description),
                    pref = screenModel.prefs.monet,
                    disabled = Build.VERSION.SDK_INT < Build.VERSION_CODES.S
                ) { screenModel.prefs.monet = it }

                SettingsItemChoice(
                    label = stringResource(R.string.appearance_theme),
                    pref = screenModel.prefs.theme,
                    labelFactory = { context.getString(it.label) }
                ) { screenModel.prefs.theme = it }
            }
        }
    }
}