package vn.tutorial.weatherapp.ui.components.daily

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import vn.tutorial.weatherapp.R
import vn.tutorial.weatherapp.data.model.weather.ForecastWeatherWithColor
import vn.tutorial.weatherapp.viewmodel.CurrentProvinceViewModel
import vn.tutorial.weatherapp.navigation.Screen
import vn.tutorial.weatherapp.ui.components.common.Humidity
import vn.tutorial.weatherapp.ui.components.common.WeatherImage
import vn.tutorial.weatherapp.ui.theme.ColorTextAction
import vn.tutorial.weatherapp.ui.theme.ColorTextPrimary
import vn.tutorial.weatherapp.utils.helper.fromHex
import vn.tutorial.weatherapp.utils.helper.myGetDateOfWeek
import vn.tutorial.weatherapp.utils.helper.myGetDayAndMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyForecast(
    modifier: Modifier = Modifier,
    data: List<ForecastWeatherWithColor>,
    controller: NavHostController,
    provinceViewModel: CurrentProvinceViewModel
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WeatherForecastHeader(
            controller = controller,
            provinceViewModel = provinceViewModel,
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(data) { item ->
                ForecastItem(item = item)
            }
        }
    }
}

@Composable
private fun WeatherForecastHeader(
    modifier: Modifier = Modifier,
    controller: NavHostController,
    provinceViewModel: CurrentProvinceViewModel

) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Weekly Forecast",
            style = MaterialTheme.typography.titleLarge,
            color = ColorTextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        ActionText(
            controller = controller,
            provinceViewModel = provinceViewModel,
        )
    }
}

@Composable
private fun ActionText(
    modifier: Modifier = Modifier,
    controller: NavHostController,
    provinceViewModel: CurrentProvinceViewModel,

    ) {
    Row(
        modifier = modifier.clickable(
            onClick = {
                val slug = provinceViewModel.currentProvince.value.slug
                controller.navigate("${Screen.MONTHLY}/$slug")
            }
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            "More",
            style = MaterialTheme.typography.titleSmall,
            color = ColorTextAction,
            fontWeight = FontWeight.Medium
        )
        Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = ColorTextAction,
            modifier = Modifier.size(20.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ForecastItem(
    item: ForecastWeatherWithColor,
    modifier: Modifier = Modifier
) {

    val updateModifier: () -> Modifier = {
        modifier
            .background(
                brush = Brush.linearGradient(
                    0.1f to Color.White,
                    1f to Color.fromHex(item.background),
                ),
                shape = RoundedCornerShape(percent = 50)
            )
    }



    Column(
        modifier = updateModifier()
            .width(65.dp)
            .padding(
                horizontal = 10.dp,
                vertical = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = myGetDateOfWeek(item.date),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Text(
            text = myGetDayAndMonth(item.date),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        WeatherImage(url = item.hour[0].condition.getIconUrl())
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = item.day.avgTempC.toInt().toString() + "°C",
            color = Color.Black
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Humidity(
            value = item.day.avgHumidity.toInt().toString(),
            color = item.color,
        )
    }
}
