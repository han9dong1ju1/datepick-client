package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val (state, effect, event) = vm.extract()

    LazyColumn(
        content = {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
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