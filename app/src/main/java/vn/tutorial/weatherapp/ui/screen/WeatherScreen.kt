package vn.tutorial.weatherapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import vn.tutorial.weatherapp.data.model.weather.convertForecastWeather
import vn.tutorial.weatherapp.viewmodel.CurrentProvinceViewModel
import vn.tutorial.weatherapp.viewmodel.ProvinceViewModel
import vn.tutorial.weatherapp.viewmodel.WeatherViewModel
import vn.tutorial.weatherapp.ui.components.daily.ActionBar
import vn.tutorial.weatherapp.ui.components.daily.AirQuality
import vn.tutorial.weatherapp.ui.components.daily.DailyForecast
import vn.tutorial.weatherapp.ui.components.daily.DrawerItem
import vn.tutorial.weatherapp.ui.components.daily.WeeklyForecast
import vn.tutorial.weatherapp.ui.theme.ColorBackground
import vn.tutorial.weatherapp.utils.helper.fromHex

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = viewModel(),
    provinceViewModel: ProvinceViewModel = viewModel(),
    currentProvinceViewModel: CurrentProvinceViewModel = viewModel(),
    controller: NavHostController

) {
    // todo tỉnh thành hiện tại
    val currentProvince = currentProvinceViewModel.currentProvince.value

    // todo gọi 1 lần duy nhất
    LaunchedEffect(Unit) {
        weatherViewModel.fetchCurrentWeather(location = currentProvince.slug)
        weatherViewModel.fetchForecastWeather(location = currentProvince.slug)
        provinceViewModel.fetchProvince()
    }

    val currentWeather = weatherViewModel.currentWeather.value ?: return
    val forecastWeather = weatherViewModel.forecastWeather.value ?: return
    val data = convertForecastWeather(forecastWeather)
    val provinces = provinceViewModel.provinces.value ?: return

    // todo quản lý sidebar
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // todo mở sidebar
    val openSideBar: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // todo các tỉnh thành
            ModalDrawerSheet(
                modifier = Modifier
                    .width(260.dp),
                drawerShape = RectangleShape,
                drawerContainerColor = Color.fromHex("#ededed")

            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(
                            rememberScrollState()
                        )
                        .background(Color.fromHex("#ededed"))

                ) {
                    Text("LOCATION", modifier = Modifier.padding(16.dp))
                    HorizontalDivider()
                    provinces.forEach { province ->
                        DrawerItem(data = province, onClick = {
                            currentProvinceViewModel.setCurrentProvince(province)
                            scope.launch {
                                drawerState.close()
                            }
                            scope.launch {
                                weatherViewModel.fetchCurrentWeather(location = province.slug)
                                weatherViewModel.fetchForecastWeather(location = province.slug)
                            }
                        })
                    }
                }
            }
        }) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = ColorBackground,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(innerPadding)
                    .padding(
                        horizontal = 24.dp, vertical = 10.dp
                    )
            ) {

                ActionBar(location = currentProvince, openSideBar = openSideBar)
                Spacer(modifier = Modifier.height(12.dp))
                DailyForecast(currentWeather = currentWeather)
                Spacer(modifier = Modifier.height(16.dp))
                AirQuality()
                Spacer(modifier = Modifier.height(16.dp))
                WeeklyForecast(
                    data = data,
                    controller = controller,
                    provinceViewModel = currentProvinceViewModel,
                )
            }
        }
    }
}