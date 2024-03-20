package br.com.fiap.smartweather.app.components.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.fiap.smartweather.R
import cafe.adriel.voyager.navigator.LocalNavigator

@Composable
fun BackButton() {
    val nav = LocalNavigator.current

    if (nav?.canPop == true) {
        IconButton(onClick = { nav.pop() }) {
            Icon(Icons.Filled.ArrowBack, stringResource(R.string.action_back))
        }
    }
}