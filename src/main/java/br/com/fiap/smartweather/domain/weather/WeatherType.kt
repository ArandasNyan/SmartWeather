package br.com.fiap.smartweather.domain.weather

import androidx.annotation.DrawableRes
import br.com.fiap.smartweather.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val nightIconRes: Int = iconRes
) {
    data object ClearSky : WeatherType(
        weatherDesc = "Céu limpo",
        iconRes = R.drawable.ic_clear,
        nightIconRes = R.drawable.ic_clear_night
    )

    data object MainlyClear : WeatherType(
        weatherDesc = "Principalmente limpo",
        iconRes = R.drawable.ic_partly_cloudy,
        nightIconRes = R.drawable.ic_partly_cloudy_night
    )

    data object PartlyCloudy : WeatherType(
        weatherDesc = "Parcialmente nublado",
        iconRes = R.drawable.ic_partly_cloudy,
        nightIconRes = R.drawable.ic_partly_cloudy_night
    )

    data object Overcast : WeatherType(
        weatherDesc = "Nublado",
        iconRes = R.drawable.ic_cloudy
    )

    data object Foggy : WeatherType(
        weatherDesc = "Nevoeiro",
        iconRes = R.drawable.ic_very_cloudy
    )

    data object DepositingRimeFog : WeatherType(
        weatherDesc = "Nevoeiro de gelo",
        iconRes = R.drawable.ic_very_cloudy
    )

    data object LightDrizzle : WeatherType(
        weatherDesc = "Garoa leve",
        iconRes = R.drawable.ic_rain_shower
    )

    data object ModerateDrizzle : WeatherType(
        weatherDesc = "Garoa moderada",
        iconRes = R.drawable.ic_rain_shower
    )

    data object DenseDrizzle : WeatherType(
        weatherDesc = "Garoa densa",
        iconRes = R.drawable.ic_rain_shower
    )

    data object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Garoa leve congelante",
        iconRes = R.drawable.ic_snowy_rainy
    )

    data object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Garoa densa congelante",
        iconRes = R.drawable.ic_snowy_rainy
    )

    data object SlightRain : WeatherType(
        weatherDesc = "Chuva leve",
        iconRes = R.drawable.ic_rainy
    )

    data object ModerateRain : WeatherType(
        weatherDesc = "Chuva moderada",
        iconRes = R.drawable.ic_rainy
    )

    data object HeavyRain : WeatherType(
        weatherDesc = "Chuva pesada",
        iconRes = R.drawable.ic_rainy
    )

    data object HeavyFreezingRain : WeatherType(
        weatherDesc = "Chuva pesada congelante",
        iconRes = R.drawable.ic_snowy_rainy
    )

    data object SlightSnowFall : WeatherType(
        weatherDesc = "Neve leve",
        iconRes = R.drawable.ic_snowy
    )

    data object ModerateSnowFall : WeatherType(
        weatherDesc = "Neve moderada",
        iconRes = R.drawable.ic_heavy_snow
    )

    data object HeavySnowFall : WeatherType(
        weatherDesc = "Neve pesada",
        iconRes = R.drawable.ic_heavy_snow
    )

    data object SnowGrains : WeatherType(
        weatherDesc = "Grãos de neve",
        iconRes = R.drawable.ic_heavy_snow
    )

    data object SlightRainShowers : WeatherType(
        weatherDesc = "Poucos chuveiros de chuva",
        iconRes = R.drawable.ic_rain_shower
    )

    data object ModerateRainShowers : WeatherType(
        weatherDesc = "Chuveiros de chuva moderados",
        iconRes = R.drawable.ic_rain_shower
    )

    data object ViolentRainShowers : WeatherType(
        weatherDesc = "Chuveiros de chuva violentos",
        iconRes = R.drawable.ic_rain_shower
    )

    data object SlightSnowShowers : WeatherType(
        weatherDesc = "Poucos chuveiros de neve",
        iconRes = R.drawable.ic_snowy
    )

    data object HeavySnowShowers : WeatherType(
        weatherDesc = "Chuveiros de neve pesados",
        iconRes = R.drawable.ic_snowy
    )

    data object ModerateThunderstorm : WeatherType(
        weatherDesc = "Trovoada moderada",
        iconRes = R.drawable.ic_thunder
    )

    data object SlightHailThunderstorm : WeatherType(
        weatherDesc = "Trovoada com granizo leve",
        iconRes = R.drawable.ic_rainythunder
    )

    data object HeavyHailThunderstorm : WeatherType(
        weatherDesc = "Trovoada com granizo pesado",
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}
