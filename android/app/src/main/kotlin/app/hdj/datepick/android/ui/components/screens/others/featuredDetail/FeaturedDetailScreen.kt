package app.hdj.datepick.android.ui.components.screens.others.featuredDetail

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.Event.ChangeStatusBarMode
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.StatusBarMode
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*
import coil.size.Scale
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun FeaturedDetailScreen(
    id: String? = null,
    prevFeatured: Featured? = null,
    vm: FeaturedDetailViewModelDelegate = hiltViewModel<FeaturedDetailViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val appViewModel = LocalDatePickAppViewModel.current

    val isHeaderScrolled = lazyListState.isFirstItemScrolled(200.dp - 56.dp)

    if (isHeaderScrolled) {
        appViewModel.event(ChangeStatusBarMode(StatusBarMode.STATUS_BAR_SYSTEM))
    } else {
        appViewModel.event(ChangeStatusBarMode(StatusBarMode.STATUS_BAR_FORCE_WHITE))
    }

    SideEffect {

        if (prevFeatured != null) {
            vm.event(FeaturedDetailViewModelDelegate.Event.ServePreviousFeaturedData(prevFeatured))
        } else if (id != null) {
            vm.event(FeaturedDetailViewModelDelegate.Event.FetchFeaturedById(id))
        }

    }


    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            val toolbarColor =
                animateColorAsState(
                    targetValue = if (isHeaderScrolled) MaterialTheme.colors.surface
                    else Color.White.copy(alpha = 0f),
                    animationSpec = tween(250)
                )

            val actionIconColor =
                animateColorAsState(
                    targetValue = if (isHeaderScrolled) MaterialTheme.colors.onSurface else Color.White,
                    animationSpec = tween(250)
                )

            val elevation =
                animateDpAsState(
                    targetValue = if (isHeaderScrolled) 4.dp else 0.dp,
                    animationSpec = tween(250)
                )

            DatePickTopAppBar(
                title = {
                    Crossfade(targetState = isHeaderScrolled) { isVisible ->
                        if (isVisible) {
                            val featured = state.featured
                            if (featured.isStateSucceed())
                                Text(
                                    featured.data.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Rounded.Share, null, tint = actionIconColor.value)
                    }
                },
                elevation = elevation.value,
                backgroundColor = toolbarColor.value,
                navigationIcon = {
                    TopAppBarBackButton(actionIconColor.value)
                }
            )
        }) {

        LazyColumn(
            state = lazyListState,
            content = {

                when (val featured = state.featured) {
                    is StateData.Failed -> {

                    }
                    is StateData.Loading -> {

                    }
                    is StateData.Success -> featuredDetailTopHeader(featured.data)
                }

                item {

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )

                }

                item {
                    Text(
                        text = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        lineHeight = 24.sp,
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                item {

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    )

                }

                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        text = "이 코스 어때요?", style = MaterialTheme.typography.h5
                    )
                }

                item {

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(20.dp)
                    ) {

                        items(4) {

                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .background(MaterialTheme.colors.onSurface.copy(0.2f))
                            )

                            if (it != 3) HorizontalMargin(20.dp)

                        }
                    }
                }

                verticalMargin(80.dp)

            }
        )

    }

}

private fun LazyListScope.featuredDetailTopHeader(featured: Featured) {
    item {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberUrlImagePainter(request = featured.photoUrl) {
                    scale(Scale.FILL)
                },
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.3f))
            )

        }
    }

    item {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = featured.title,
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = featured.description,
                style = MaterialTheme.typography.subtitle2
            )

        }

    }

}

@Composable
@Preview
fun FeaturedDetailScreenPreview() {
    DatePickTheme {
        FeaturedDetailScreen(vm = fakeFeaturedDetailViewModel())
    }
}