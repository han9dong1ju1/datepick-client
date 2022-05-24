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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.hdj.datepick.android.ui.components.list.PlaceQueryFilter
import app.hdj.datepick.android.ui.components.list.PlaceQueryFilterShimmer
import app.hdj.datepick.android.ui.components.list.PlaceVertialListItem
import app.hdj.datepick.android.ui.destinations.CheckboxSelectDialogDestination
import app.hdj.datepick.android.ui.destinations.KakaoPlaceSearchScreenDestination
import app.hdj.datepick.android.ui.dialog.CheckboxSelectDialogConfig
import app.hdj.datepick.android.ui.dialog.CheckboxSelectDialogResult
import app.hdj.datepick.android.utils.LoadStateAnimatedContent
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onPlaceClicked
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.presentation.main.MapScreenViewModelDelegate
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
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient


@Composable
@Destination
fun PlaceListScreen(
    navigator: DestinationsNavigator,
    placeQueryParams: PlaceQueryParams,
    resultRecipient: ResultRecipient<CheckboxSelectDialogDestination, CheckboxSelectDialogResult>
) {

    val vm = hiltViewModel<PlaceListScreenViewModel>()

    val (state) = vm.extract()

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            vm.event(
                SearchPlaces(
                    state.placesQueryResult.queryParams.run {
                        copy(filterParams = filterParams.copy(categoryIds = it.value.selected))
                    }
                )
            )
        }
    }

    LaunchedEffect(true) {
        vm.event(SearchPlaces(placeQueryParams))
    }

    PlaceListScreenContent(
        vm = vm,
        onPlaceClicked = navigator.onPlaceClicked,
        onKakaoPlaceSearchClicked = { navigator.navigate(KakaoPlaceSearchScreenDestination) },
        onCategoryRemove = {
            state.placesQueryResult.queryParams.run {
                val previousCategories = filterParams.categoryIds
                if (!previousCategories.isNullOrEmpty()) {
                    vm.event(
                        SearchPlaces(
                            copy(
                                filterParams = filterParams.copy(
                                    categoryIds = previousCategories - it.id
                                )
                            )
                        )
                    )
                }
            }
        },
        onCategoryChangeClicked = { selectedCategoryIds ->
            navigator.navigate(CheckboxSelectDialogDestination(CheckboxSelectDialogConfig(
                "카테고리 선택",
                "카테고리를 선택해주세요.",
                state.placeCategoriesState.dataOrNull?.map { it.id to it.name }
                    .orEmpty(),
                selectedCategoryIds,
            )))
        }
    )
}

@Composable
private fun PlaceListScreenContent(
    vm: PlaceListScreenViewModelDelegate,
    onPlaceClicked: (Place) -> Unit = {},
    onKakaoPlaceSearchClicked: () -> Unit = {},
    onCategoryRemove : (Place.Category) -> Unit = {},
    onCategoryChangeClicked : (List<Long>) -> Unit = {}
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val lazyPagingItems = state.placesQueryResult.result.collectAsLazyPagingItems()

    val refreshState = lazyPagingItems.loadState.refresh
    val appendState = lazyPagingItems.loadState.append

    val swipeRefreshState =  rememberSwipeRefreshState(refreshState is LoadState.Loading)

    BaseSwipeRefreshLayoutScaffold(
        swipeRefreshState = swipeRefreshState,
        onRefresh = { lazyPagingItems.refresh() },
        topBar = {
            Column {
                InsetTopBar(
                    title = {
                        Text(text = state.placesQueryResult.queryParams.filterParams.keyword ?: "장소 검색")
                    },
                    navigationIcon = { TopAppBarBackButton() },
                    enableDivider = false
                )

                LoadStateAnimatedContent(
                    loadState = state.placeCategoriesState,
                    onSuccess = {
                        PlaceQueryFilter(
                            categories = it,
                            placeQueryParams = state.placesQueryResult.queryParams,
                            onQueryChanged = { placeQueryParams -> SearchPlaces(placeQueryParams) },
                            onCategoryChangeClicked = onCategoryChangeClicked,
                            onCategoryRemove =  onCategoryRemove
                        )
                    },
                    onLoading = {
                        PlaceQueryFilterShimmer()
                    }
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
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
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .animateItemPlacement()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center)
                                .animateItemPlacement()
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