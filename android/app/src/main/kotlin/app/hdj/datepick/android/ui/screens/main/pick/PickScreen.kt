package app.hdj.datepick.android.ui.screens.main.pick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun PickScreen(vm: PickViewModelDelegate = hiltViewModel<PickViewModel>()) {

    val (state, effect, event) = vm.extract()

    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    val navController = LocalAppNavController.current

    val me = LocalMe.current

    val pagerState = rememberPagerState()

    BaseScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetSmallTopAppBar(
                title = { Text(text = "Pick") },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            CustomScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = {
                    Box(
                        Modifier
                            .tabIndicatorOffset(it[pagerState.currentPage])
                            .height(4.dp)
                            .padding(horizontal = 10.dp)
                            .background(
                                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                },
                edgePadding = 10.dp
            ) {
                val coroutineScope = rememberCoroutineScope()
                listOf("장소", "코스").forEachIndexed { index, label ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    ) {
                        Text(
                            text = label,
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 14.dp)
                        )
                    }
                }
            }

            ViewPager(
                modifier= Modifier.fillMaxSize(),
                pagerState = pagerState,
                list = listOf(0, 1)
            ) { _, _ ->
                Text("Hello")
            }
        }

    }

}

@Composable
@Preview
fun PickScreenPreview() {
    BaseTheme {
        PickScreen(fakePickViewModel())
    }
}