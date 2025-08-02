package vn.tutorial.weatherapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import vn.tutorial.weatherapp.navigation.Navigation

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val controller = rememberNavController()
    Navigation(
        controller = controller
    )
}