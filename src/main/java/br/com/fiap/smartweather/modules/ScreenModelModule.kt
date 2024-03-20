package br.com.fiap.smartweather.modules


import br.com.fiap.smartweather.app.screensmodels.AppearancePreferencesScreenModel
import br.com.fiap.smartweather.app.screensmodels.UnitPreferencesScreenModel
import br.com.fiap.smartweather.app.screensmodels.DailyWeatherScreenModel
import br.com.fiap.smartweather.app.screensmodels.HourlyWeatherScreenModel
import br.com.fiap.smartweather.app.screensmodels.LocationPickerScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModelModule = module {
    factoryOf(::AppearancePreferencesScreenModel)
    factoryOf(::UnitPreferencesScreenModel)
    factoryOf(::LocationPickerScreenModel)
    factoryOf(::HourlyWeatherScreenModel)
    factoryOf(::DailyWeatherScreenModel)
}