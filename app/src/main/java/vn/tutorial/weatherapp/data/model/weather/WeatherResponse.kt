package vn.tutorial.weatherapp.data.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current") val current: WeatherData,
    @SerializedName("forecast") val forecast: Forecast
)