package vn.tutorial.weatherapp.data.api

import android.os.Build
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vn.tutorial.weatherapp.BuildConfig
import vn.tutorial.weatherapp.data.model.weather.WeatherResponse
import vn.tutorial.weatherapp.utils.helper.getHourNow

interface WeatherAPIService {
    @RequiresApi(Build.VERSION_CODES.O)
    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") location: String,
        @Query("aqi") airQuality: String = "yes",
        @Query("hour") hour: String = getHourNow(),
    ): Call<WeatherResponse>

    @GET("forecast.json")
    fun getForecastWeather(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") location: String,
        @Query("days") days: Int = 7,
        @Query("aqi") airQuality: String = "yes",
        @Query("hour") hour: Int = 8,
    ): Call<WeatherResponse>
}

