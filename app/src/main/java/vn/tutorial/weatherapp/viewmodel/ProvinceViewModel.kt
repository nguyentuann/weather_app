package vn.tutorial.weatherapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.tutorial.weatherapp.data.api.RetrofitClient
import vn.tutorial.weatherapp.data.model.province.ProvinceData
import vn.tutorial.weatherapp.data.model.province.ProvinceResponse

class ProvinceViewModel : ViewModel() {
    private val _provinces = mutableStateOf<List<ProvinceData>?>(null)

    val provinces: State<List<ProvinceData>?> = _provinces

    fun fetchProvince() {
        RetrofitClient.api_province.getProvinces().enqueue(
            object : Callback<ProvinceResponse> {
                override fun onResponse(
                    call: Call<ProvinceResponse?>,
                    response: Response<ProvinceResponse?>
                ) {
                    if (response.isSuccessful) {
                        val sortedList = response.body()?.data?.sortedBy { it.name }
                        _provinces.value = sortedList
                    }
                }

                override fun onFailure(call: Call<ProvinceResponse?>, t: Throwable) {
                    Log.e("ProvinceViewModel", "Failed to load provinces", t)
                }
            }
        )
    }
}

class CurrentProvinceViewModel : ViewModel() {
    private val _currentProvince = mutableStateOf<ProvinceData>(
        ProvinceData(
            id = "1",
            name = "Hà Nội",
        )
    )

    val currentProvince: State<ProvinceData> = _currentProvince

    fun setCurrentProvince(province: ProvinceData) {
        _currentProvince.value = province
    }

}