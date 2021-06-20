package app.hdj.datepick.ui.screens.main.map

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.screens.main.pick.PickViewModel
import app.hdj.datepick.ui.utils.extract

@Composable
fun MapScreen() {

    val (state, effect, event) = hiltViewModel<MapViewModel>().extract()



}