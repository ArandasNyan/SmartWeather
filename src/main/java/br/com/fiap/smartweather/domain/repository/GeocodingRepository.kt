package br.com.fiap.smartweather.domain.repository


import br.com.fiap.smartweather.domain.geocoding.GeocodingData
import br.com.fiap.smartweather.domain.mappers.toGeocodingData
import br.com.fiap.smartweather.domain.remote.GeocodingApi
import br.com.fiap.smartweather.domain.util.Resource

class GeocodingRepository(private val api: GeocodingApi) {
    suspend fun getGeocodingData(location: String): Resource<List<GeocodingData>> {
        return try {
            Resource.Success(
                data = api.getGeocodingData(location = location).toGeocodingData()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}