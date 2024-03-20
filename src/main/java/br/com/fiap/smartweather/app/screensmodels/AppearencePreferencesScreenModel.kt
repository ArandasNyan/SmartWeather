package br.com.fiap.smartweather.app.screensmodels


import android.content.Context
import android.os.Build
import androidx.annotation.StringRes
import br.com.fiap.smartweather.R
import br.com.fiap.smartweather.domain.manager.BasePreferenceManager
import cafe.adriel.voyager.core.model.ScreenModel

class AppearancePreferenceManager(context: Context) :
    BasePreferenceManager(context.getSharedPreferences("prefs", Context.MODE_PRIVATE)) {
    var theme by enumPreference("theme", Theme.SYSTEM)
    var monet by booleanPreference("monet", Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
}

enum class Theme(@StringRes val label: Int) {
    SYSTEM(R.string.theme_system),
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark);
}

class AppearancePreferencesScreenModel(
    val prefs: AppearancePreferenceManager
) : ScreenModel