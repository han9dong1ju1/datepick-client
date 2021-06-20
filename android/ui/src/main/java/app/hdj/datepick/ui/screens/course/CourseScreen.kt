package app.hdj.datepick.ui.screens.course

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.utils.extract

@Composable
fun CourseScreen() {

    val (state, effect, event) = hiltViewModel<CourseViewModel>().extract()




}