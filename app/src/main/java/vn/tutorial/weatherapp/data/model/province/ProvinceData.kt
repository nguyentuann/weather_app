package vn.tutorial.weatherapp.data.model.province

import com.google.gson.annotations.SerializedName
import vn.tutorial.weatherapp.utils.helper.toSlug

class ProvinceData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
) {
    val slug: String
        get() = toSlug(name)
}

