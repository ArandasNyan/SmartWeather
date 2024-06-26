package br.com.fiap.smartweather.app.screensmodels

import android.content.Context
import androidx.annotation.StringRes
import br.com.fiap.smartweather.R
import br.com.fiap.smartweather.domain.manager.BasePreferenceManager
import cafe.adriel.voyager.core.model.ScreenModel

class UnitPreferenceManager(context: Context) :
    BasePreferenceManager(context.getSharedPreferences("prefs", Context.MODE_PRIVATE)) {

    var tempUnit by enumPreference("temp_unit", TempUnit.CELSIUS)
    var windUnit by enumPreference("wind_unit", WindUnit.KMH)
    var precipitationUnit by enumPreference("precipitation_unit", PrecipitationUnit.MM)
}

enum class TempUnit(@StringRes val label: Int) {
    CELSIUS(R.string.celsius),
    FAHRENHEIT(R.string.fahrenheit),
}

enum class WindUnit(@StringRes val label: Int) {
    KMH(R.string.kmh),
    MS(R.string.ms),
    MPH(R.string.mph),
    KN(R.string.kn),
}

enum class PrecipitationUnit(@StringRes val label: Int) {
    MM(R.string.mm),
    INCH(R.string.inch)
}

class UnitPreferencesScreenModel(
    val prefs: UnitPreferenceManager
) : ScreenModel