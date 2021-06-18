package app.hdj.datepick.ui.screens.main.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.utils.extract

@Composable
fun HomeScreen() {

    val (state, effect, event) = hiltViewModel<HomeViewModel>().extract()



}