package br.com.fiap.smartweather.modules


import br.com.fiap.smartweather.app.screensmodels.AppearancePreferenceManager
import br.com.fiap.smartweather.app.screensmodels.UnitPreferenceManager
import br.com.fiap.smartweather.app.screensmodels.LocationPreferenceManager

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::AppearancePreferenceManager)
    singleOf(::UnitPreferenceManager)
    singleOf(::LocationPreferenceManager)
}