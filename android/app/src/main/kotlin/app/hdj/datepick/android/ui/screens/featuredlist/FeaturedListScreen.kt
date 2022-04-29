package app.hdj.datepick.android.ui.screens.featuredlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.hdj.datepick.android.ui.components.list.FeaturedListItem
import app.hdj.datepick.android.ui.providers.WindowSize
import app.hdj.datepick.android.ui.providers.rememberWindowSizeClass
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onFeaturedClicked
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.presentation.featuredlist.FeaturedListScreenViewModel
import app.hdj.datepick.presentation.featuredlist.FeaturedListScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseSwipeRefreshLayoutScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.items
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun FeaturedListScreen(
    navigator: DestinationsNavigator
) {
    val vm = hiltViewModel<FeaturedListScreenViewModel>()

    FeaturedListScreenContent(
        vm = vm,
        onFeaturedClicked = navigator.onFeaturedClicked
    )
}

@Composable
private fun FeaturedListScreenContent(
    vm: FeaturedListScreenViewModelDelegate,
    onFeaturedClicked: (Featured) -> Unit
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyGridState()

    val lazyPagingItems = state.featured.collectAsLazyPagingItems()

    val refreshState = lazyPagingItems.loadState.refresh
    val appendState = lazyPagingItems.loadState.append

    val swipeRefreshState = rememberSwipeRefreshState(refreshState == LoadState.Loading)

    BaseSwipeRefreshLayoutScaffold(
        swipeRefreshState = swipeRefreshState,
        onRefresh = { lazyPagingItems.refresh() },
        topBar = {
            Column {
                InsetTopBar(
                    navigationIcon = { TopAppBarBackButton() },
                    enableDivider = false
                )
                Divider(color = MaterialTheme.colors.onBackground.copy(0.05f))
            }
        }
    ) {

        val column = when (rememberWindowSizeClass()) {
            WindowSize.Expanded -> 4
            WindowSize.Medium -> 2
            else -> 1
        }

        LazyVerticalGrid(
            GridCells.Fixed(column),
            state = lazyListState,
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {

            val itemCount = lazyPagingItems.itemCount

            if (itemCount != 0) {
                items(lazyPagingItems) { featured ->
                    featured?.let {
                        FeaturedListItem(
                            modifier = Modifier
                                .animateItemPlacement()
                                .padding(10.dp),
                            featured = featured,
                            onFeaturedClicked = onFeaturedClicked
                        )
                    }
                }
            }

            if (refreshState == LoadState.Loading || appendState == LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .animateItemPlacement()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center)
                                .animateItemPlacement()
                        )
                    }
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }

            if (refreshState is LoadState.Error || appendState is LoadState.Error) {
                item {

                }
            }

            items(column) {
                Column {
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }
        }

    }

}