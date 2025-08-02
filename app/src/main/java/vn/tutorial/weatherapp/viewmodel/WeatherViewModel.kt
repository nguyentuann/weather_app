package vn.tutorial.weatherapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.tutorial.weatherapp.data.api.RetrofitClient
import vn.tutorial.weatherapp.data.model.weather.Forecast
import vn.tutorial.weatherapp.data.model.weather.WeatherData
import vn.tutorial.weatherapp.data.model.weather.WeatherResponse

class WeatherViewModel : ViewModel() {
    private val _currentWeather = mutableStateOf<WeatherData?>(null)
    private val _forecastWeather = mutableStateOf<Forecast?>(null)
    private val _monthlyForecast = mutableStateOf<Forecast?>(null)

    val currentWeather: State<WeatherData?> = _currentWeather
    val forecastWeather: State<Forecast?> = _forecastWeather
    val monthlyForecast: State<Forecast?> = _monthlyForecast

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchCurrentWeather(location: String) {
        RetrofitClient.api_weather.getCurrentWeather(location = location).enqueue(
            object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        _currentWeather.value = response.body()?.current
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("WeatherViewModel", "Failed to load weather", t)
                }
            }
        )
    }

    fun fetchForecastWeather(location: String, days: Int = 7) {
        RetrofitClient.api_weather.getForecastWeather(location = location, days = days)
            .enqueue(
                object : Callback<WeatherResponse> {
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        if (response.isSuccessful) {
                            if (days == 7) {
                                _forecastWeather.value = response.body()?.forecast
                                Log.d("Success", "Forecast weather loaded successfully")
                            } else {
                                _monthlyForecast.value = response.body()?.forecast
                                Log.d("Success", "Monthly forecast loaded successfully")
                            }
                        } else {
                            Log.e(
                                "WeatherViewModel",
                                "Error loading forecast weather: ${
                                    response.errorBody()?.string()
                                }"
                            )
                        }
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        Log.e("WeatherViewModel", "Failed to load forecast weather", t)
                    }
                }
            )
    }
}