package br.com.fiap.smartweather.modules


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import br.com.fiap.smartweather.domain.remote.GeocodingApi
import br.com.fiap.smartweather.domain.remote.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient.Builder
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val activeNetwork =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

private val contentType = "application/json".toMediaType()
private val json = Json { ignoreUnknownKeys = true }

val appModule = module {
    fun provideWeatherApi(context: Context): WeatherApi {
        val cacheControlInterceptor = Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            if (isNetworkAvailable(context)) {
                val maxAge = 60 * 60 * 1
                originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
            } else {
                val maxStale = 60 * 60 * 24 * 14
                originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
            }
        }

        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(context.cacheDir, cacheSize.toLong())
        val builder = Builder()
            .cache(cache)
        builder.networkInterceptors().add(cacheControlInterceptor)
        val okHttpClient = builder.build()

        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create()
    }

    fun provideGeocodingApi(): GeocodingApi {
        return Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create()
    }

    singleOf(::provideWeatherApi)
    singleOf(::provideGeocodingApi)
}