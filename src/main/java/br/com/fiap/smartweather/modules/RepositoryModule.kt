package br.com.fiap.smartweather.modules


import br.com.fiap.smartweather.domain.repository.GeocodingRepository
import br.com.fiap.smartweather.domain.repository.WeatherRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::WeatherRepository)
    singleOf(::GeocodingRepository)
}