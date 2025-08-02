package vn.tutorial.weatherapp.ui.components.daily

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import vn.tutorial.weatherapp.R
import vn.tutorial.weatherapp.data.model.weather.WeatherData
import vn.tutorial.weatherapp.ui.theme.ColorGradient1
import vn.tutorial.weatherapp.ui.theme.ColorGradient2
import vn.tutorial.weatherapp.ui.theme.ColorGradient3
import vn.tutorial.weatherapp.ui.theme.ColorTextSecondary
import vn.tutorial.weatherapp.ui.theme.ColorTextSecondaryVariant
import vn.tutorial.weatherapp.ui.theme.ColorWindForecast
import vn.tutorial.weatherapp.utils.helper.myFormatDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecast(
    modifier: Modifier = Modifier,
    currentWeather: WeatherData
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (forecastImage, forecastValue, windImage, title, description, background) = createRefs()
        CardBackground(
            modifier = Modifier.constrainAs(background) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = parent.top,
                    bottom = description.bottom,
                    topMargin = 24.dp,
                )
                height = Dimension.fillToConstraints
            }
        )
        // todo icon
        Image(
            painter = painterResource(
                id = if (currentWeather.isDay == 1)
                    R.drawable.img_sun
                else
                    R.drawable.img_moon_stars
            ),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(top = 48.dp, start = 20.dp)
                .height(120.dp)
                .constrainAs(forecastImage) {
                    start.linkTo(anchor = parent.start, margin = 24.dp)
                    top.linkTo(anchor = parent.top)
                }
        )
        // todo description
        Text(
            text = currentWeather.condition.text,
            style = MaterialTheme.typography.titleLarge,
            color = ColorTextSecondary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(anchor = parent.start, margin = 20.dp)
                    top.linkTo(anchor = forecastImage.bottom)

                }
                .width(160.dp)
        )
        //todo date
        Text(
            text = myFormatDate(currentWeather.lastUpdated.split(" ")[0]),
            style = MaterialTheme.typography.bodyMedium,
            color = ColorTextSecondaryVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(description) {
                    start.linkTo(anchor = title.start)
                    top.linkTo(anchor = title.bottom)
                }
                .padding(bottom = 32.dp)
                .width(160.dp)
        )

        // todo temperature and feels like
        ForecastValue(
            degree = currentWeather.tempC.toInt().toString(),
            feelsLike = currentWeather.feelsLikeC.toInt().toString(),
            modifier = Modifier.constrainAs(forecastValue) {
                end.linkTo(anchor = parent.end, margin = 24.dp)
                top.linkTo(anchor = forecastImage.top)
                bottom.linkTo(anchor = forecastImage.bottom)
            },
        )

        // todo forecast
        WindForecast(
            modifier = Modifier.constrainAs(windImage) {
                linkTo(
                    top = title.top, bottom = title.bottom
                )
                end.linkTo(
                    anchor = parent.end, margin = 24.dp
                )
            })

    }
}

@Composable
private fun CardBackground(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0f to ColorGradient1, 0.5f to ColorGradient2, 1f to ColorGradient3
                ), shape = RoundedCornerShape(32.dp)
            )
    )
}

@Composable
private fun ForecastValue(
    modifier: Modifier = Modifier,
    degree: String = "30",
    feelsLike: String = "32",
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.Start
    ) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                degree, style = TextStyle(
                    brush = Brush.linearGradient(
                        0f to Color.White,
                        1f to Color.White.copy(alpha = 0.3f),
                    ), fontSize = 80.sp, fontWeight = FontWeight.Black
                ), modifier = Modifier.padding(end = 20.dp)
            )
            Text(
                "°", style = TextStyle(
                    brush = Brush.linearGradient(
                        0f to Color.White,
                        1f to Color.White.copy(alpha = 0.3f),
                    ), fontSize = 70.sp, fontWeight = FontWeight.Light
                )
            )
        }
        Text(
            "Feels like $feelsLike°C",
            style = MaterialTheme.typography.bodyMedium,
            color = ColorTextSecondaryVariant,
        )
    }
}

@Composable
private fun WindForecast(
    modifier: Modifier = Modifier,

    ) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_wind_qality),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = ColorWindForecast
        )

        Icon(
            painter = painterResource(R.drawable.ic_wind),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = ColorWindForecast
        )
    }
}