package app.hdj.datepick.android.ui.screens.main.pick

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PickScreen(vm: PickViewModelDelegate = hiltViewModel<PickViewModel>()) {

    val (state, effect, event) = vm.extract()

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setStatusBarColor(Color.White.copy(0.5f), useDarkIcons)
    }

    BaseScaffold(modifier = Modifier.fillMaxSize()) {

    }

}

@Composable
@Preview
fun PickScreenPreview() {
    BaseTheme {
        PickScreen(fakePickViewModel())
    }
}