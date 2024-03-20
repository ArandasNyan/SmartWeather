package br.com.fiap.smartweather.domain.mappers


import br.com.fiap.smartweather.domain.geocoding.GeocodingData
import br.com.fiap.smartweather.domain.remote.GeocodingDto

fun GeocodingDto.toGeocodingData(): List<GeocodingData> {
    return results.map {
        GeocodingData(
            location = "${if(it.name == it.country) "" else "${it.name}, "}${if(it.admin == null) "" else "${it.admin}, "}${it.country}",
            longitude = it.longitude,
            latitude = it.latitude,
        )
    }
}