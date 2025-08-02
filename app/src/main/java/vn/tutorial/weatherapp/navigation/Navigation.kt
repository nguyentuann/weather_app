package vn.tutorial.weatherapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import vn.tutorial.weatherapp.ui.screen.MonthlyForecastScreen
import vn.tutorial.weatherapp.ui.screen.WeatherScreen

class Screen {
    companion object {
        const val HOME = "home"
        const val MONTHLY = "monthly"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    controller: NavHostController
) {
    NavHost(
        navController = controller,
        startDestination = Screen.HOME,
    ) {
        composable(Screen.HOME) {
            WeatherScreen(controller = controller)
        }
        composable(
            route = "${Screen.MONTHLY}/{slug}",
            arguments = listOf(
                navArgument("slug") { type = NavType.StringType })
        ) { backStackEntry ->
            val slug = backStackEntry.arguments?.getString("slug") ?: return@composable
            MonthlyForecastScreen(controller = controller, slug = slug)
        }
    }
}