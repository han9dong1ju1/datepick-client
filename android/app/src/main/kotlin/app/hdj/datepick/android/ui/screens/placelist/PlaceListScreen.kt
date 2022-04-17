package app.hdj.datepick.android.ui.screens.placelist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.hdj.datepick.android.ui.components.list.PlaceVertialListItem
import app.hdj.datepick.android.ui.destinations.KakaoPlaceSearchScreenDestination
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onPlaceClicked
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.presentation.placelist.PlaceListScreenViewModel
import app.hdj.datepick.presentation.placelist.PlaceListScreenViewModelDelegate
import app.hdj.datepick.presentation.placelist.PlaceListScreenViewModelDelegate.Event.SearchPlaces
import app.hdj.datepick.ui.components.BaseSwipeRefreshLayoutScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.styles.tertiary
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber


@Composable
@Destination
fun PlaceListScreen(
    navigator: DestinationsNavigator,
    placeQueryParams: PlaceQueryParams
) {

    val vm = hiltViewModel<PlaceListScreenViewModel>()

    LaunchedEffect(true) {
        vm.event(SearchPlaces(placeQueryParams))
    }

    PlaceListScreenContent(
        vm = vm,
        onPlaceClicked = navigator.onPlaceClicked,
        onKakaoPlaceSearchClicked = {
            navigator.navigate(KakaoPlaceSearchScreenDestination)
        }
    )
}

@Composable
private fun PlaceListScreenContent(
    vm: PlaceListScreenViewModelDelegate,
    onPlaceClicked: (Place) -> Unit = {},
    onKakaoPlaceSearchClicked: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val lazyPagingItems = state.places.collectAsLazyPagingItems()

    val refreshState = lazyPagingItems.loadState.refresh
    val appendState = lazyPagingItems.loadState.append

    val swipeRefreshState =  rememberSwipeRefreshState(refreshState is LoadState.Loading)

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

        LazyColumn(
            modifier = Modifier.padding(it),
            state = lazyListState,
        ) {

            val itemCount = lazyPagingItems.itemCount

            if (itemCount != 0) {
                (0..itemCount).forEach { index ->
                    val currentItem = lazyPagingItems.runCatching { peek(index) }.getOrNull()
                    item(currentItem?.id) {
                        val item = lazyPagingItems.runCatching { get(index) }.getOrNull()
                        item?.let {
                            PlaceVertialListItem(item, onPlaceClicked)
                        }
                    }

                    if (index >= 40 && index.rem(40) == 0) {
                        item {
                            Surface(
                                onClick = onKakaoPlaceSearchClicked,
                                modifier = Modifier.fillMaxWidth().padding(20.dp),
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colors.tertiary
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Search,
                                        contentDescription = null,
                                        tint = MaterialTheme.colors.secondary
                                    )

                                    Spacer(Modifier.width(10.dp))

                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = "찾으시는 장소가 없나요?\n직접 추가해보세요!"
                                    )

                                    Spacer(Modifier.width(10.dp))

                                    Icon(
                                        imageVector = Icons.Rounded.ChevronRight,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }

                }

            }

            if (refreshState == LoadState.Loading || appendState == LoadState.Loading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(20.dp).animateItemPlacement()) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp).align(Alignment.Center).animateItemPlacement()
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }

            if (refreshState is LoadState.Error || appendState is LoadState.Error) {
                item {

                }
            }
        }

    }

}