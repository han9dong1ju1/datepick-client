package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val (state, effect, event) = vm.extract()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .background(Color.Black)
                ) {
                    HomeScreenFeaturedPager(emptyList())
                    HomeScreenTopBar()
                }
            }
        }
    )

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}