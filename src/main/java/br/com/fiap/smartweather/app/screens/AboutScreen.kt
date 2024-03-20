package br.com.fiap.smartweather.app.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import br.com.fiap.smartweather.app.components.navigation.LargeToolbar

class AboutScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(topBar = { LargeToolbar(title = { Text(text = "Sobre") }, backButton = true) }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {}
        }
    }
}