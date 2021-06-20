package app.hdj.datepick.ui.screens.place

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.utils.extract

@Composable
fun PlaceScreen(

) {

    val (state, effect, event) = hiltViewModel<PlaceViewModel>().extract()



}