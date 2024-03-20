package br.com.fiap.smartweather.app.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.fiap.smartweather.R
import br.com.fiap.smartweather.app.tabs.SettingsTab
import br.com.fiap.smartweather.app.tabs.TodayTab
import br.com.fiap.smartweather.app.tabs.WeekTab
import cafe.adriel.voyager.navigator.tab.TabNavigator

@Composable
fun BottomBar(navigator: TabNavigator) {
    NavigationBar {
        NavigationBarItem(
            selected = navigator.current == TodayTab,
            onClick = { navigator.current = TodayTab },
            label = { Text(text = stringResource(id = R.string.tab_today)) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(id = R.string.tab_today)
                )
            })
        NavigationBarItem(
            selected = navigator.current == WeekTab,
            onClick = { navigator.current = WeekTab },
            label = { Text(text = stringResource(id = R.string.tab_weekly)) },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Weekly"
                )
            })
        NavigationBarItem(
            selected = navigator.current == SettingsTab,
            onClick = { navigator.current = SettingsTab },
            label = { Text(text = stringResource(id = R.string.tab_settings)) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            })
    }
}