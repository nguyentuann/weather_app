package vn.tutorial.weatherapp.ui.components.daily

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import vn.tutorial.weatherapp.R
import vn.tutorial.weatherapp.data.model.province.ProvinceData
import vn.tutorial.weatherapp.ui.components.common.customShadow
import vn.tutorial.weatherapp.ui.theme.ColorGradient1
import vn.tutorial.weatherapp.ui.theme.ColorGradient2
import vn.tutorial.weatherapp.ui.theme.ColorGradient3
import vn.tutorial.weatherapp.ui.theme.ColorImageShadow
import vn.tutorial.weatherapp.ui.theme.ColorSurface
import vn.tutorial.weatherapp.ui.theme.ColorTextPrimary
import vn.tutorial.weatherapp.ui.theme.ColorTextSecondaryVariant

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    location: ProvinceData,
    openSideBar: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ControlButton(onClick = openSideBar)
        LocationInfo(
            location = location,
            modifier = Modifier.padding(
                top = 4.dp
            )
        )
        ProfileButton()
    }
}

@Composable
private fun ControlButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(48.dp)
            .customShadow(
                color = Color.Black,
                alpha = 0.15f,
                borderRadius = 48.dp,
                shadowRadius = 16.dp,
                offsetY = 4.dp,
                offsetX = 4.dp
            ), color = ColorSurface, shape = CircleShape
    ) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_control),
                contentDescription = "Control Button",
                modifier = Modifier.clickable(
                    onClick = onClick
                )
            )
        }
    }
}

@Composable
private fun ProfileButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .border(width = 1.dp, color = ColorSurface, shape = CircleShape)
            .customShadow(
                color = ColorImageShadow,
                alpha = 0.7f,
                borderRadius = 48.dp,
                shadowRadius = 12.dp,
                offsetY = 6.dp,
                offsetX = 6.dp
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = "Profile Button",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}

@Composable
private fun LocationInfo(
    modifier: Modifier = Modifier,
    location: ProvinceData
) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_pin),
                contentDescription = "Location Icon",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(18.dp)
            )
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleLarge,
                color = ColorTextPrimary,
                fontWeight = FontWeight.Bold
            )
        }
        ProgressBar()
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    0f to ColorGradient1,
                    0.25f to ColorGradient2,
                    1f to ColorGradient3,
                ), shape = RoundedCornerShape(8.dp)
            )
            .padding(
                vertical = 2.dp, horizontal = 10.dp
            )
    ) {
        Text(
            "Updating...",
            style = MaterialTheme.typography.labelSmall,
            color = ColorTextSecondaryVariant
        )
    }
}