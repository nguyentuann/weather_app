package vn.tutorial.weatherapp.data.model.weather

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import vn.tutorial.weatherapp.R

data class WeatherData(
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: WeatherCondition,

    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("uv") val uv: Double,
    @SerializedName("air_quality") val airQuality: AirQuality,
)

class WeatherCondition(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
) {
    fun getIconUrl(): String {
        return "https:$icon"
    }
}

class AirQuality(
    @SerializedName("so2") val so2: Double,
    @SerializedName("o3") val o3: Double,
)

data class AirQualityItem(
    @DrawableRes val icon: Int,
    val title: String,
    val value: String
)

fun convertAirQuality(currentWeatherData: WeatherData): List<AirQualityItem> {
    return listOf(
        AirQualityItem(
            title = "Real Feel",
            value = currentWeatherData.feelsLikeC.toString(),
            icon = R.drawable.ic_real_feel
        ),
        AirQualityItem(
            title = "Wind",
            value = currentWeatherData.windMph.toString(),
            icon = R.drawable.ic_wind_qality,
        ),
        AirQualityItem(
            title = "SO2",
            value = currentWeatherData.airQuality.so2.toString(),
            icon = R.drawable.ic_so2
        ),
        AirQualityItem(
            title = "Humidity",
            value = currentWeatherData.humidity.toString()+ "%",
            icon = R.drawable.ic_rain_chance
        ),
        AirQualityItem(
            title = "UV Index",
            value = currentWeatherData.uv.toString(),
            icon = R.drawable.ic_uv_index
        ),
        AirQualityItem(
            title = "OЗ",
            value = currentWeatherData.airQuality.o3.toString(),
            icon = R.drawable.ic_o3
        )
    )
}
