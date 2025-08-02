package vn.tutorial.weatherapp.ui.components.monthly

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vn.tutorial.weatherapp.data.model.weather.ForecastWeatherWithColor
import vn.tutorial.weatherapp.ui.components.common.Humidity
import vn.tutorial.weatherapp.utils.helper.fromHex
import vn.tutorial.weatherapp.utils.helper.myGetDateOfWeek
import vn.tutorial.weatherapp.utils.helper.myGetDayAndMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthlyForecast(
    item: ForecastWeatherWithColor,
    modifier: Modifier = Modifier
) {

    val cornerRadius = 36.dp
    val shape = RoundedCornerShape(cornerRadius)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .background(
                    brush = Brush.linearGradient(
                        0.1f to Color.White,
                        1f to Color.fromHex(item.background),
                    ),
                )
                .border(1.dp, Color.LightGray, shape = shape),
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxHeight(),
                model = item.hour[0].condition.getIconUrl(),
                contentDescription = null,
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = myGetDateOfWeek(item.date),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = myGetDayAndMonth(item.date),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                }
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = item.day.avgTempC.toInt().toString() + "°C",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Humidity(
                    value = item.day.avgHumidity.toInt().toString(),
                    color = item.color,
                    modifier = Modifier.width(90.dp)
                )
                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    item.hour[0].condition.text,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),

                )
            }
        }
    }
}
