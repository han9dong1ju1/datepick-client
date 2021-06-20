package app.hdj.datepick.ui.screens.main.pick

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.utils.extract

@Composable
fun PickScreen() {

    val (state, effect, event) = hiltViewModel<PickViewModel>().extract()



}