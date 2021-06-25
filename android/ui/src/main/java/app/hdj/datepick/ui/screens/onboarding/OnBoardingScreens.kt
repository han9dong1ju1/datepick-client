package app.hdj.datepick.ui.screens.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.screens.others.login.LoginScreen
import app.hdj.datepick.ui.screens.onboarding.register.RegisterScreen
import app.hdj.datepick.ui.screens.onboarding.splash.SplashScreen

fun NavGraphBuilder.onBoardingScreens() {

    navigation(
        startDestination = NavigationGraph.OnBoarding.Splash.route,
        route = NavigationGraph.OnBoarding.route
    ) {

        composable(NavigationGraph.OnBoarding.Splash.route) { SplashScreen() }

        composable(NavigationGraph.OnBoarding.Login.route) { LoginScreen() }

        composable(NavigationGraph.OnBoarding.Register.route) { RegisterScreen() }

    }

}