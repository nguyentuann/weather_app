package vn.tutorial.weatherapp.ui.components.daily

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import vn.tutorial.weatherapp.R
import vn.tutorial.weatherapp.data.model.weather.AirQualityItem
import vn.tutorial.weatherapp.data.model.weather.convertAirQuality
import vn.tutorial.weatherapp.viewmodel.WeatherViewModel
import vn.tutorial.weatherapp.ui.components.common.customShadow
import vn.tutorial.weatherapp.ui.theme.ColorAirQualityIconTitle
import vn.tutorial.weatherapp.ui.theme.ColorSurface
import vn.tutorial.weatherapp.ui.theme.ColorTextPrimary
import vn.tutorial.weatherapp.ui.theme.ColorTextPrimaryVariant

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AirQuality(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel()
) {
    val currentWeather = viewModel.currentWeather.value
    val data = convertAirQuality(currentWeather ?: return)
    val refreshWeather: () -> Unit = {
        viewModel.fetchCurrentWeather(location = "Can Tho")
    }


    Surface(
        modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(32.dp), color = ColorSurface
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = 18.dp, horizontal = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AirQualityHeader(
                onClick =
                    refreshWeather
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                data.onEach { item ->
                    AirQualityInfo(data = item, modifier = Modifier.weight(1f))
                }
            }
        }

    }
}

@Composable
private fun AirQualityHeader(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_air_quality_header),
                contentDescription = null,
                tint = ColorAirQualityIconTitle,
                modifier = Modifier.size(32.dp)
            )
            Text(
                "Air Quality", style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp
                )
            )
        }
        RefreshButton(onClick = onClick)
    }
}

@Composable
private fun RefreshButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        color = ColorSurface, shape = CircleShape, modifier = modifier
            .clickable(
                onClick = onClick
            )
            .size(32.dp)
            .customShadow(
                color = Color.Black, alpha = 0.15f, shadowRadius = 32.dp, offsetY = 4.dp
            )
    ) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_refresh),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun AirQualityInfo(
    modifier: Modifier = Modifier,
    data: AirQualityItem
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(data.icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = ColorAirQualityIconTitle
        )

        Column {
            Text(
                data.title,
                style = MaterialTheme.typography.labelSmall,
                color = ColorTextPrimaryVariant
            )
            Text(
                data.value,
                style = MaterialTheme.typography.labelSmall,
                color = ColorTextPrimary
            )
        }
    }
}