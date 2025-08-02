package vn.tutorial.weatherapp.data.model.province

import com.google.gson.annotations.SerializedName

class ProvinceResponse(
    @SerializedName("data") val data: List<ProvinceData>,
)
