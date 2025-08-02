package vn.tutorial.weatherapp.utils.helper

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import coil.util.Logger
import java.time.LocalDate


@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
fun myFormatDate(input: String): String {
    val date = LocalDate.parse(input)
    val dayOfWeek = when (date.dayOfWeek.value) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> ""
    }

    val day = String.format("%02d", date.dayOfMonth)
    val month = date.monthValue
    return "$dayOfWeek, $day - $month"
}

@RequiresApi(Build.VERSION_CODES.O)
fun myGetDateOfWeek(input: String): String {
    val date = LocalDate.parse(input)
    return when (date.dayOfWeek.value) {
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "Thu"
        5 -> "Fri"
        6 -> "Sat"
        7 -> "Sun"
        else -> ""
    }
}

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
fun myGetDayAndMonth(input: String): String {
    val date = LocalDate.parse(input)
    val day = String.format("%02d", date.dayOfMonth)
    val month = date.monthValue
    return "$day/$month"
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("DefaultLocale")
fun getHourNow(): String {
    val currentHour = java.time.LocalTime.now().hour
    Log.d("now", "Current hour: $currentHour")
    return String.format("%02d", currentHour)
}
