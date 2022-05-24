package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.searchbox.SearchBoxDropdownItem
import app.hdj.datepick.android.ui.components.searchbox.SearchBoxItem
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.android.utils.LoadStateAnimatedContent
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams.PagingParams.Sort
import app.hdj.datepick.ui.components.ImmutableRatingBar
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.components.shimmer

@Composable
fun PlaceHorizontalList(
    list: List<Place>,
    onPlaceClicked: (Place) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        itemsIndexed(list) { index, place ->
            PlaceCardListItem(
                modifier = Modifier.width(200.dp),
                place, onPlaceClicked
            )
            if (list.lastIndex != index) Spacer(Modifier.width(20.dp))
        }
    }
}

@Composable
fun PlaceVertialListItem(
    place: Place,
    onPlaceClicked: (Place) -> Unit
) {
    app.hdj.datepick.ui.components.BasicListItem(
        title = place.name,
        subtitle = place.address,
        rightSideUi = place.imageUrl?.let {
            {
                NetworkImage(
                    url = it,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
            }
        },
        onClick = { onPlaceClicked(place) }
    )
}

@Composable
fun PlaceCardListItem(
    modifier: Modifier = Modifier,
    place: Place,
    onPlaceClicked: (Place) -> Unit
) {
    Surface(
        onClick = { onPlaceClicked(place) },
        modifier = modifier.height(260.dp),
        color = Color.Unspecified
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            NetworkImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(10.dp),
                url = place.imageUrl,
                onFailed = {
                    Box(
                        modifier = Modifier
                            .height(140.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                MaterialTheme.colors.onBackground
                                    .copy(0.1f)
                                    .compositeOver(MaterialTheme.colors.background)
                            )
                    ) {

                        Icon(
                            imageVector = DatePickIcons.Logo,
                            contentDescription = null,
                            modifier = Modifier
                                .width(100.dp)
                                .align(Alignment.Center),
                            tint = MaterialTheme.colors.onBackground.copy(0.2f)
                                .compositeOver(MaterialTheme.colors.background)
                        )

                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(place.name, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier.alpha(0.8f),
                text = place.address,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.alpha(0.6f),
                text = place.categories.joinToString(", ") { it.name },
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            place.rating?.toFloat()?.let {
                Spacer(modifier = Modifier.height(4.dp))
                ImmutableRatingBar(rating = it, starSize = 16.dp)
            }
        }

    }
}

@Composable
fun PlacesCardHeaderCarousel(
    title: String,
    placeState: LoadState<List<Place>>,
    onPlaceClicked: (Place) -> Unit,
    onMoreClicked: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(10.dp))
        Header(title, "더보기", onMoreButtonClicked = onMoreClicked)

        LoadStateAnimatedContent(loadState = placeState,
            onLoading = {
                Row(
                    modifier = Modifier.horizontalScroll(
                        rememberScrollState(),
                        enabled = false
                    )
                ) {
                    repeat(4) {
                        Spacer(Modifier.width(20.dp))
                        PlaceCardShimmer()
                    }
                }
            },
            onSuccess = {
                PlaceHorizontalList(it, onPlaceClicked)
            })

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun PlaceCardShimmer() {

    Column(
        modifier = Modifier.size(200.dp, 260.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .shimmer(shape = RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .width(160.dp)
                .height(16.dp)
                .shimmer(shape = RoundedCornerShape(6.dp))
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .width(100.dp)
                .height(12.dp)
                .shimmer(shape = RoundedCornerShape(6.dp))
        )

    }
}

@Composable
fun PlacesCardGridWithHeaderWithQuery(
    title: String,
    categoryState: LoadState<List<Place.Category>>,
    placeState: LoadState<List<Place>>,
    onPlaceClicked: (Place) -> Unit,
    onMoreClicked: () -> Unit,
    placeQueryParams: PlaceQueryParams,
    onQueryChanged: (PlaceQueryParams) -> Unit,
    onCategoryRemove: (Place.Category) -> Unit,
    onCategoryChangeClicked: (List<Long>) -> Unit,
) {
    if (!placeState.isStateFailed()) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(10.dp))

            Header(title, "더보기", onMoreButtonClicked = onMoreClicked)

            LoadStateAnimatedContent(
                loadState = categoryState,
                onSuccess = {
                    PlaceQueryFilter(
                        it,
                        placeQueryParams,
                        onQueryChanged,
                        onCategoryRemove,
                        onCategoryChangeClicked
                    )
                },
                onLoading = {
                    PlaceQueryFilterShimmer()
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            LoadStateAnimatedContent(loadState = placeState,
                onLoading = {
                    Row(
                        modifier = Modifier.horizontalScroll(
                            rememberScrollState(),
                            enabled = false
                        )
                    ) {
                        repeat(4) {
                            Spacer(Modifier.width(20.dp))
                            PlaceCardShimmer()
                        }
                    }
                },
                onSuccess = {
                    PlaceHorizontalList(it, onPlaceClicked)
                })


            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}

@Composable
fun PlaceQueryFilterShimmer() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState(), false)
            .height(48.dp)
    ) {

        Spacer(modifier = Modifier.width(20.dp))

        repeat(4) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .shimmer(shape = RoundedCornerShape(100.dp)),
            )
            Spacer(modifier = Modifier.width(10.dp))
        }


    }
}

@Composable
fun PlaceQueryFilter(
    categories: List<Place.Category>,
    placeQueryParams: PlaceQueryParams,
    onQueryChanged: (PlaceQueryParams) -> Unit,
    onCategoryRemove: (Place.Category) -> Unit,
    onCategoryChangeClicked: (List<Long>) -> Unit
) {

    val selectedCategories = remember(placeQueryParams) {
        categories.filter { placeQueryParams.filterParams.categoryIds?.contains(it.id) == true }
    }

    val sortOptions = remember { Sort.values() }

    val sortOptionsWithText = remember {
        sortOptions.associate {
            when (it) {
                Sort.Latest -> it to "최신순"
                Sort.Pick -> it to "많이 찜한 순"
                Sort.Popular -> it to "유명한 순"
                Sort.RatingDesc -> it to "별점 높은 순"
                Sort.RatingAsc -> it to "별점 낮은 순"
                Sort.Distance -> it to "가까운 순"
            }
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        item {
            Spacer(modifier = Modifier.width(20.dp))

            SearchBoxDropdownItem(
                placeQueryParams.pagingParams.sort,
                sortOptions.toList(),
                { sortOptionsWithText.getOrDefault(it, "") }
            ) {
                onQueryChanged(
                    placeQueryParams.copy(
                        pagingParams = placeQueryParams.pagingParams.copy(
                            sort = it
                        )
                    )
                )
            }
        }

        if (placeQueryParams.filterParams.latitude != null &&
            placeQueryParams.filterParams.longitude != null
        ) {

            item {
                Spacer(modifier = Modifier.width(10.dp))

                SearchBoxDropdownItem(
                    (placeQueryParams.filterParams.distance ?: 1.0),
                    listOf(0.1, 0.5, 1.0, 2.0, 5.0),
                    { if (it >= 1.0) "${it.toInt()}km 이내" else "${(it * 1000).toInt()}m 이내" }
                ) {
                    onQueryChanged(
                        placeQueryParams.copy(
                            filterParams = placeQueryParams.filterParams.copy(distance = it)
                        )
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.width(10.dp))

            SearchBoxItem("카테고리") {
                onCategoryChangeClicked(
                    selectedCategories.map { it.id }
                )
            }
        }

        items(selectedCategories) {
            Row(
                modifier = Modifier.animateItemPlacement(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(10.dp))

                Chip(
                    modifier = Modifier.height(40.dp),
                    onClick = { onCategoryRemove(it) },
                    colors = ChipDefaults.chipColors(
                        backgroundColor = MaterialTheme.colors.onSurface.copy(0.05f)
                    )
                ) {
                    Text(text = it.name, style = MaterialTheme.typography.subtitle2)
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        Icons.Rounded.Cancel, null,
                        tint = MaterialTheme.colors.onSurface.copy(0.5f),
                        modifier = Modifier.size(20.dp)
                    )
                }

            }
        }

        item {
            Spacer(modifier = Modifier.width(20.dp))
        }

    }
}