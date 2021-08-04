package app.hdj.datepick.android.ui.components.screens.others.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun SplashScreen(
    vm: SplashViewModelDelegate = hiltViewModel<SplashViewModel>(),
    onTimeOut: () -> Unit
) {

    val (state) = vm.extract()

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {

//        LaunchedEffect(meState) {
//            if (meState !is app.hdj.datepick.domain.StateData.Loading) {
//                delay(1000)
//                onTimeOut()
//            }
//        }

        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(100.dp),
            imageVector = Icons.Rounded.Favorite,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
        )

    }

}


@Composable
@Preview
fun SplashScreenPreview() {
    DatePickTheme {
        SplashScreen { }
    }
}