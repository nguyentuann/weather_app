package vn.tutorial.weatherapp.data.model.weather

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastWeather>
)

class ForecastWeather(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: ForecastDay,
    @SerializedName("hour") val hour: List<Hour>,
)

class ForecastWeatherWithColor(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: ForecastDay,
    @SerializedName("hour") val hour: List<Hour>,
    val color: String,
    val background: String = "#FFFFFF" // Default background color
)

class ForecastDay(
    @SerializedName("avgtemp_c")
    val avgTempC: Double,

    @SerializedName("avghumidity")
    val avgHumidity: Int,
)

class Hour(
    @SerializedName("condition") val condition: WeatherCondition
)

fun convertForecastWeather(forecast: Forecast): List<ForecastWeatherWithColor> {
    return forecast.forecastDay.map { forecastWeather ->
        ForecastWeatherWithColor(
            date = forecastWeather.date,
            day = forecastWeather.day,
            hour = forecastWeather.hour,
            color = when (forecastWeather.day.avgHumidity) {
                in 0..50 -> "#f52626"
                in 50..79 -> "#fcec1a"
                in 80..100 -> "#29c0f9"
                else -> "#FFFFFF" // Default color if out of range
            },
            background = when (forecastWeather.day.avgTempC) {
                in -30.0..0.0 -> "#ADD8E6"
                in 0.1..26.0 -> "#79fe48"
                in 26.1..100.0 -> "#fe4848"
                else -> "#FFFFFF" // Default background color if out of range
            }
        )
    }
}
