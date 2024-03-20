package br.com.fiap.smartweather.domain.remote

import kotlinx.serialization.Serializable

@Serializable
data class
GeocodingDto(
    val results: List<GeocodingLocationDto> = listOf()
)