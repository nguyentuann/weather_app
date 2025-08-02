package vn.tutorial.weatherapp.ui.components.daily

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.tutorial.weatherapp.data.model.province.ProvinceData

@Composable
fun DrawerItem(
    data: ProvinceData,
    onClick: (ProvinceData) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
            .clickable(
                onClick = {
                    onClick(data)
                }
            ),
    ) {
        Text(data.name, style = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,

        ))
    }
}