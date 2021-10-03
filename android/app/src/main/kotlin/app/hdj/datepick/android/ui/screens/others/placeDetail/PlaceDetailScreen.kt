package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.PlaceListWithHeader
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openWebUrl
import app.hdj.datepick.android.ui.screens.others.placeDetail.PlaceDetailViewModelDelegate.Effect.ErrorOccurred
import app.hdj.datepick.domain.mapOrNull
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.navigationBarsHeight
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

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

    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    BaseScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {

        BaseCollapsingToolbarScaffold(
            Modifier.fillMaxSize(),
            state = collapsingToolbarScaffoldState,
            topBar = {
                BaseCollapsingTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = { TopAppBarBackButton() },
                    background = {
                        Surface(
                            modifier = Modifier
                                .pin()
                                .fillMaxWidth()
                                .height(180.dp)
                        ) {}
                    },
                    content = {
                        val progress = collapsingToolbarScaffoldState.toolbarState.progress
                        val textSize = (16 + (24 - 16) * progress).sp
                        Text(
                            modifier = Modifier.padding(it),
                            fontSize = textSize,
                            text = state.place.mapOrNull { it.name }.orEmpty()
                        )
                    },
                    titleWhenCollapsed = Alignment.BottomStart,
                    titleWhenExpanded = Alignment.BottomStart
                )
            }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                Spacer(Modifier.height(10.dp))

                PlaceDetailScreenBlogReviewSection(blogReviewState = state.blogReviews) {
                    navController.openWebUrl(it.url)
                }

                PlaceDetailScreenReviewSection {

                }

                Spacer(Modifier.height(10.dp))

                state.place.mapOrNull { it }?.let { it1 ->
                    PlaceDetailScreenMapLocationSection(it1) {
                        navController.openWebUrl(it)
                    }
                }

                Spacer(Modifier.height(10.dp))

                PlaceListWithHeader("주변에 있는 또다른 장소", state.nearbyPlaces)
                Spacer(Modifier.height(10.dp))

                PlaceListWithHeader("이 장소는 어때요?", state.similarPlaces)
                Spacer(Modifier.height(10.dp))

                Header(title = "이 장소가 포함된 코스")
                LazyRow(
                    modifier = Modifier
                        .background(MaterialTheme.colors.surface)
                        .fillMaxWidth()
                        .height(300.dp)
                ) {

                }
                Spacer(Modifier.height(10.dp))

                Spacer(Modifier.navigationBarsHeight(additional = 20.dp))
            }

        }

    }

}