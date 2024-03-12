package br.com.fiap.smartweather.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Zinc900,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Esquema de cores para o modo claro
private val LightColorScheme = lightColorScheme(
    // Cores principais
    primary = Zinc900,         // Cor principal (roxo) para o modo claro
    secondary = PurpleGrey40,   // Cor secundária (roxo cinza) para o modo claro
    tertiary = Pink40,           // Terceira cor (rosa) para o modo claro

    /* Outras cores padrão para substituir */
    background = Zinc900,       // Cor de fundo
    surface = Zinc50,           // Cor da superfície
    onPrimary = Zinc50,               // Cor do texto sobre a cor principal
    onSecondary = Zinc600,             // Cor do texto sobre a cor secundária
    onTertiary = Zinc50,              // Cor do texto sobre a terceira cor
    onBackground = Zinc800,      // Cor do texto sobre o fundo
    onSurface = Zinc50,          // Cor do texto sobre a superfície
)

@Composable
fun SmartWeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}