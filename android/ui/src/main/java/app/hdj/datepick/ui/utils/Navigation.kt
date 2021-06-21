package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory

@Composable
fun NavController.currentScreenRoute(): String {
    val currentRoute = currentBackStackEntry?.destination?.route
    return currentRoute!!
}