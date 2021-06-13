package app.hdj.datepick.android.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainUi() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {

        }
    ) {

        NavHost(navController = navController, startDestination = "") {

            composable("") {

            }

        }

    }


}