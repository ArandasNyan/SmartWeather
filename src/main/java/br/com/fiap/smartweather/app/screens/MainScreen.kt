package br.com.fiap.smartweather.app.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.fiap.smartweather.app.components.location.LocationsDrawer
import br.com.fiap.smartweather.app.components.navigation.SmallToolbar
import br.com.fiap.smartweather.app.tabs.TodayTab
import br.com.fiap.smartweather.domain.util.NavigationTab
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import kotlinx.coroutines.launch
import br.com.fiap.smartweather.R
import br.com.fiap.smartweather.app.components.navigation.BottomBar

class MainScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val drawerState =
            rememberDrawerState(initialValue = DrawerValue.Closed)
        val coroutineScope = rememberCoroutineScope()

        TabNavigator(tab = TodayTab) {
            LocationsDrawer(
                drawerState = drawerState,
                onClose = { coroutineScope.launch { drawerState.close() } }
            ) {
                Scaffold(
                    topBar = {
                        SmallToolbar(
                            title = {
                                Text(
                                    text = it.current.options.title,
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee()
                                )
                            },
                            actions = {
                                (it.current as? NavigationTab)?.Actions()
                            }
                        ) {
                            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = stringResource(id = R.string.location_picker_open)
                                )
                            }
                        }
                    },
                    bottomBar = {
                        BottomBar(navigator = it)
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        CurrentScreen()
                    }
                }
            }
        }
    }
}