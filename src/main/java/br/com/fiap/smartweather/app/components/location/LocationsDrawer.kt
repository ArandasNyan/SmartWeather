package br.com.fiap.smartweather.app.components.location

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.fiap.smartweather.app.screens.LocationPickerScreen
import br.com.fiap.smartweather.app.screensmodels.LocationPreferenceManager
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.compose.koinInject
import br.com.fiap.smartweather.R

@Composable
fun LocationsDrawer(
    drawerState: DrawerState,
    onClose: () -> Unit,
    children: @Composable () -> Unit
) {
    val locationPreferenceManager: LocationPreferenceManager = koinInject()
    val navigator = LocalNavigator.current?.parent

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    val locations = locationPreferenceManager.locations

                    Text(
                        text = stringResource(id = R.string.locations),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    locations.forEachIndexed { index, data ->
                        val selected = index == locationPreferenceManager.selectedIndex
                        NavigationDrawerItem(
                            label = { Text(text = data.location, overflow = TextOverflow.Ellipsis, maxLines = 1) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = stringResource(
                                        id = R.string.location
                                    )
                                )
                            },
                            selected = selected,
                            onClick = {
                                onClose()
                                locationPreferenceManager.selectedIndex = index
                            },
                            badge = {
                                IconButton(onClick = {
                                    locationPreferenceManager.locations -= data
                                    if (selected) locationPreferenceManager.selectedIndex = 0
                                    if (locationPreferenceManager.locations.isEmpty()) navigator?.push(
                                        LocationPickerScreen()
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(
                                            id = R.string.action_delete
                                        )
                                    )
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(id = R.string.location_add)) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.location_add)
                            )
                        },
                        selected = true,
                        onClick = { navigator?.push(LocationPickerScreen()) },
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        children()
    }
}