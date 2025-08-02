package vn.tutorial.weatherapp.utils.helper

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

fun Color.Companion.fromHex(colorString: String) =
    Color(colorString.toColorInt())
