package br.com.fiap.smartweather.app.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LargeToolbar(
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    backButton: Boolean = false
) {
    LargeTopAppBar(
        title = title,
        navigationIcon = { if (backButton) BackButton() },
        actions = actions,
    )
}