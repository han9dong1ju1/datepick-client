package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddLocationAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.list.PlaceBlogReviewListItem
import app.hdj.datepick.android.ui.list.PlaceListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.ui.screens.openWebUrl
import app.hdj.datepick.android.ui.screens.others.placeDetail.PlaceDetailViewModelDelegate.Effect.ErrorOccurred
import app.hdj.datepick.android.utils.foldComposable
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.domain.getOrNull
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.icon.LikeIconButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.isFirstItemScrolled
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import coil.size.Scale
import com.google.accompanist.insets.navigationBarsHeight

@Composable
fun PlaceDetailScreen(
    placeId: Long? = null,
    place: Place? = null,
    vm: PlaceDetailViewModelDelegate = hiltViewModel<PlaceDetailViewModel>(),
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

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
                    if (placeState.isStateSucceed()) {

                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Rounded.AddLocationAlt,
                                contentDescription = null
                            )
                        }

                        LikeIconButton(placeState.data.isPicked) {

                        }

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
                    Spacer(Modifier.height(20.dp))
                    PlaceDetailScreenHeader(placeState = placeState)
                    Spacer(Modifier.height(20.dp))
                    PlaceDetailMapCard(placeState = placeState)
                    Spacer(Modifier.height(20.dp))
                }
            }

            Spacer(Modifier.height(10.dp))

            Header(title = "사진")

            LazyRow(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 20.dp, bottom = 20.dp, end = 20.dp)
            ) {

                val photos = state.place.getOrNull { it.photos } ?: emptyList()

                itemsIndexed(photos) { index, photo ->
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(MaterialTheme.shapes.small),
                        contentScale = ContentScale.Crop,
                        painter = rememberUrlImagePainter(request = photo) {
                            scale(Scale.FIT)
                        },
                        contentDescription = null
                    )
                    if (index != photos.lastIndex)
                        Spacer(modifier = Modifier.width(20.dp))
                }
            }

            Spacer(Modifier.height(10.dp))

            Header(title = "블로그 리뷰")

            state.blogReviews.onSucceedComposable { list ->
                list.forEach { blogReview ->
                    PlaceBlogReviewListItem(blogReview) { clickedReview ->
                        navController.openWebUrl(clickedReview.url)
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            Header(title = "리뷰")

            LazyRow(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

            }
            Spacer(Modifier.height(10.dp))

            Header(title = "이 장소는 어때요?")

            Column(modifier = Modifier.fillMaxWidth()) {
                state.similarPlaces.foldComposable(onSucceed = { list ->
                    list.forEach { place ->
                        PlaceListItem(place = place, onPlaceClicked = navController::openPlace)
                    }
                })
            }

            Spacer(Modifier.height(10.dp))

            Header(title = "이 장소가 포함된 코스")

            LazyRow(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

            }

            Spacer(Modifier.navigationBarsHeight(additional = 20.dp))

        }

    }

}