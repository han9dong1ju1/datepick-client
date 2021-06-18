package app.hdj.datepick.ui.navigation

sealed class NavigationGraph(val routeName: String) {

    object Login : NavigationGraph("login")

    object Main : NavigationGraph("main")

    object Settings : NavigationGraph("settings")

}