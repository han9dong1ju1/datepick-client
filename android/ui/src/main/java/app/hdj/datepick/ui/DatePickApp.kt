package app.hdj.datepick.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.screens.login.LoginScreen
import app.hdj.datepick.ui.screens.main.home.HomeScreen
import app.hdj.datepick.ui.screens.setting.SettingScreen
import app.hdj.datepick.ui.styles.DatePickTheme
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun DatePickApp() {

    DatePickTheme {

        ProvideWindowInsets {

            val navController = rememberNavController()

            Scaffold(
                bottomBar = {

                }
            ) {


                NavHost(
                    navController = navController,
                    startDestination = NavigationGraph.Login.routeName
                ) {

                    composable(NavigationGraph.Login.routeName) { LoginScreen() }

                    composable(NavigationGraph.Main.routeName) { HomeScreen() }

                    composable(NavigationGraph.Settings.routeName) { SettingScreen() }

                }

            }

        }

    }

}