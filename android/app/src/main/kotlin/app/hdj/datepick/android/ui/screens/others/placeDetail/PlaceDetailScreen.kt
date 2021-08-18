package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.list.PlaceBlogReviewListItem
import app.hdj.datepick.android.ui.screens.others.placeDetail.PlaceDetailViewModelDelegate.Effect.ErrorOccurred
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.icon.LikeIconButton
import app.hdj.datepick.ui.utils.*

@Composable
fun PlaceDetailScreen(
    placeId: Long? = null,
    place: Place? = null,
    vm: PlaceDetailViewModelDelegate = hiltViewModel<PlaceDetailViewModel>(),
) {

    val (state, effect, event) = fakePlaceDetailViewModel().extract()

    val scrollState = rememberScrollState()

    val scaffoldState = rememberScaffoldState()

    val isHeaderScrolled = scrollState.isFirstItemScrolled(limit = 100.dp)

    LaunchedEffect(true) {
        if (place != null) {
            event(PlaceDetailViewModelDelegate.Event.ShowPassedPlace(place))
        } else {
            placeId?.let { event(PlaceDetailViewModelDelegate.Event.RequestPlace(it)) }
        }
    }

    effect.collectInLaunchedEffect {
        if (it is ErrorOccurred) {
            scaffoldState.snackbarHostState.showSnackbar(it.message)
        }
    }

    val placeState = state.place

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            val elevationAnimate by animateDpAsState(targetValue = if (isHeaderScrolled) 4.dp else 0.dp)

            DatePickTopAppBar(
                elevation = elevationAnimate,
                navigationIcon = { TopAppBarBackButton() },
                actions = {
                    if (placeState.isStateSucceed())
                        LikeIconButton(placeState.data.isPicked) {

                        }
                },
                title = {
                    AnimatedVisibility(
                        visible = isHeaderScrolled,
                        enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                        exit = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut()
                    ) {
                        if (placeState.isStateSucceed()) Text(text = placeState.data.name)
                    }
                }
            )
        }
    ) {

        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(top = it.calculateTopPadding())
        ) {

            Surface(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    PlaceDetailScreenHeader(placeState = placeState)
                    PlaceDetailMapCard(placeState = placeState)
                }
            }

            VerticalMargin(10.dp)

            Header(title = "사진")

            LazyRow(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 20.dp, bottom = 20.dp, end = 20.dp)
            ) {

                val list = (0..10).toList()
                itemsIndexed(list) { index, _ ->
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(MaterialTheme.shapes.small),
                        painter = rememberUrlImagePainter(request = "https://picsum.photos/200") {

                        },
                        contentDescription = null
                    )
                    if (index != list.lastIndex)
                        Spacer(modifier = Modifier.width(20.dp))
                }
            }

            VerticalMargin(10.dp)

            Header(title = "블로그 리뷰")

            repeat(5) {
                PlaceBlogReviewListItem()
            }

            VerticalMargin(10.dp)

            Header(title = "리뷰")

            LazyRow(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

            }

            VerticalMargin(10.dp)

            Header(title = "이 장소가 포함된 코스")

            LazyRow(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

            }

            VerticalMargin(10.dp)


        }

    }

}