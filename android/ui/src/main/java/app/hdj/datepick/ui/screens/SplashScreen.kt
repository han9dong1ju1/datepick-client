package app.hdj.datepick.ui.screens

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.styles.DatePickTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onTimeOut: () -> Unit
) {

    Box(modifier = modifier
        .background(MaterialTheme.colors.background)
        .fillMaxSize()
    ) {

        LaunchedEffect(true) {
            delay(2000)
            onTimeOut()
        }

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