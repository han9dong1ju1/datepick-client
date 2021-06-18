package app.hdj.datepick.ui.screens.main.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.utils.extract

@Composable
fun ProfileScreen() {

    val (state, effect, event) = hiltViewModel<ProfileViewModel>().extract()



}