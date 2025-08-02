package vn.tutorial.weatherapp.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vn.tutorial.weatherapp.data.model.province.ProvinceResponse

interface ProvinceAPIService {
    @GET("provinces")
    fun getProvinces(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 63,
    ): Call<ProvinceResponse>
}