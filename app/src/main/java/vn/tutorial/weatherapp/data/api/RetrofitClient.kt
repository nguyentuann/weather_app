package vn.tutorial.weatherapp.data.api

import vn.tutorial.weatherapp.utils.constant.Url

object RetrofitClient {
    val api_weather: WeatherAPIService by lazy {
        retrofit2.Retrofit.Builder().baseUrl(Url.WEATHER_URL).addConverterFactory(
            retrofit2.converter.gson.GsonConverterFactory.create()
        ).build().create(
            WeatherAPIService::class.java
        )
    }

    val api_province: ProvinceAPIService by lazy {
        retrofit2.Retrofit.Builder().baseUrl(Url.PROVINCE_URL).addConverterFactory(
            retrofit2.converter.gson.GsonConverterFactory.create()
        ).build().create(
            ProvinceAPIService::class.java
        )
    }
}