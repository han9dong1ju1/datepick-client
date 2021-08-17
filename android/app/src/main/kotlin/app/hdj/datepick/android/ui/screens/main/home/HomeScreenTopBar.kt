package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.styles.DatePickTheme

@Composable
fun HomeScreenTopBar() {

    DatePickTopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        title = {
            Image(
                imageVector = DatePickIcons.Logo, contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    )

}

@Composable
@Preview
fun HomeScreenTopBarPreview() {
    DatePickTheme {
        HomeScreenTopBar()
    }
}