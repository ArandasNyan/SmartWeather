package br.com.fiap.smartweather.app.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.GpsFixed
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import br.com.fiap.smartweather.R
import br.com.fiap.smartweather.app.components.settings.SettingsCategory
import br.com.fiap.smartweather.app.screens.AboutScreen
import br.com.fiap.smartweather.app.screens.AppearanceSettingsScreen
import br.com.fiap.smartweather.app.screens.LocationPickerScreen
import br.com.fiap.smartweather.app.screens.UnitsScreen
import br.com.fiap.smartweather.domain.util.NavigationTab
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object SettingsTab : NavigationTab, Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.tab_settings)
            val icon = rememberVectorPainter(Icons.Default.Settings)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Column {
            SettingsCategory(
                icon = Icons.Outlined.Palette,
                text = stringResource(R.string.settings_appearance),
                subtext = stringResource(R.string.settings_appearance_description),
                destination = ::AppearanceSettingsScreen
            )
            SettingsCategory(
                icon = Icons.Outlined.GpsFixed,
                text = stringResource(R.string.settings_location),
                subtext = stringResource(R.string.settings_location_description),
                destination = ::LocationPickerScreen
            )
            SettingsCategory(
                icon = Icons.Outlined.AcUnit,
                text = stringResource(R.string.settings_units),
                subtext = stringResource(R.string.settings_units_description),
                destination = ::UnitsScreen
            )
        }
    }

    @Composable
    override fun Actions() {
        val navigator = LocalNavigator.current?.parent

        IconButton(onClick = { navigator?.push(AboutScreen()) }) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(R.string.action_open_about)
            )
        }
    }
}