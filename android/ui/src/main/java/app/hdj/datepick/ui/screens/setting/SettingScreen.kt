package app.hdj.datepick.ui.screens.setting

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.utils.extract

@Composable
fun SettingScreen() {

    val (state, effect, event) = hiltViewModel<SettingViewModel>().extract()


}