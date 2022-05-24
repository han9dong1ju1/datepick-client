package app.hdj.datepick.android.ui.screens.featured

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.CourseCardListItem
import app.hdj.datepick.android.ui.components.list.CourseHorizontalList
import app.hdj.datepick.android.ui.components.list.Header
import app.hdj.datepick.ui.components.shimmer
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onCourseClicked
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.presentation.featured.FeaturedDetailScreenViewModel
import app.hdj.datepick.presentation.featured.FeaturedDetailScreenViewModelDelegate
import app.hdj.datepick.presentation.featured.FeaturedDetailScreenViewModelDelegate.Event.LoadWithFeatured
import app.hdj.datepick.presentation.featured.FeaturedDetailScreenViewModelDelegate.Event.LoadWithFeaturedId
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.utils.DEEPLINK_URL
import app.hdj.datepick.utils.EXTERNAL_DEEPLINK_URL
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
@Destination
fun FeaturedDetailScreen(
    navigator: DestinationsNavigator,
    featured: Featured
) {
    FeaturedDetailScreenContent(
        featured = featured,
        vm = hiltViewModel<FeaturedDetailScreenViewModel>(),
        onCourseClicked = navigator.onCourseClicked
    )
}

@Composable
@Destination(
    deepLinks = [
        DeepLink(uriPattern = "$DEEPLINK_URL/featured/{featuredId}"),
        DeepLink(uriPattern = "$EXTERNAL_DEEPLINK_URL/featured/{featuredId}"),
    ]
)
fun FeaturedDetailScreenFromDeepLink(
    navigator: DestinationsNavigator,
    featuredId: Long,
) {
    FeaturedDetailScreenContent(
        featuredId = featuredId,
        vm = hiltViewModel<FeaturedDetailScreenViewModel>(),
        onCourseClicked = navigator.onCourseClicked
    )
}

@Composable
private fun FeaturedDetailScreenContent(
    featured: Featured? = null,
    featuredId: Long? = null,
    vm: FeaturedDetailScreenViewModelDelegate,
    onCourseClicked: (Course) -> Unit = {},
) {
    val (state, effect, event) = vm.extract()

    val context = LocalContext.current

    effect.collectInLaunchedEffect {
        when (it) {
            is FeaturedDetailScreenViewModelDelegate.Effect.ShowShareMenu -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, it.shareUrl)
                    type = "text/plain"
                }

                context.startActivity(shareIntent)
            }
        }
    }

    LaunchedEffect(true) {
        if (featuredId != null) event(LoadWithFeaturedId(featuredId))
        else if (featured != null) event(LoadWithFeatured(featured))
    }

    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    val systemUiController = rememberSystemUiController()

    val isHeaderScrolled = collapsingToolbarScaffoldState.toolbarState.progress <= 0.8

    val showDarkForeground = remember(isHeaderScrolled, state.featured) {
        if (state.featured == null) true
        else isHeaderScrolled
    }

    val defaultStatusBarDarkContentEnabled = remember {
        systemUiController.statusBarDarkContentEnabled
    }

    if (MaterialTheme.colors.isLight) {
        DisposableEffect(showDarkForeground) {
            systemUiController.statusBarDarkContentEnabled = showDarkForeground
            onDispose {
                systemUiController.statusBarDarkContentEnabled = defaultStatusBarDarkContentEnabled
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            InsetBottomBar {

                IconButton(onClick = {
                    event(FeaturedDetailScreenViewModelDelegate.Event.OpenShareMenu)
                }) {
                    Icon(Icons.Rounded.Share, null)
                }

                IconButton(onClick = {

                }) {
                    Icon(Icons.Rounded.Favorite, null)
                }

            }
        }
    ) {
        BasicCollapsingToolbarScaffold(
            state = collapsingToolbarScaffoldState,
            background = {
                val animatedToolbarBackgroundColor by animateColorAsState(
                    MaterialTheme.colors.surface.copy(1 - collapsingToolbarScaffoldState.toolbarState.progress)
                )

                Crossfade(
                    targetState = state.featured,
                    modifier = Modifier.parallax(),
                ) { featured ->
                    if (featured != null) {
                        Surface(color = animatedToolbarBackgroundColor) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .graphicsLayer {
                                        alpha = collapsingToolbarScaffoldState.toolbarState.progress
                                    }

                            ) {
                                NetworkImage(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = Color.Black)
                                        .alpha(0.5f)
                                        .height(400.dp),
                                    url = featured.imageUrl
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .background(color = MaterialTheme.colors.surface)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(20.dp)
                            ) {
                                Spacer(
                                    modifier = Modifier
                                        .shimmer(color = MaterialTheme.colors.onSurface.copy(0.5f))
                                        .size(200.dp, 26.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Spacer(
                                    modifier = Modifier
                                        .shimmer(color = MaterialTheme.colors.onSurface.copy(0.5f))
                                        .size(300.dp, 14.dp)
                                )
                            }
                        }
                    }
                }

            },
            foreground = {
                val titleSize =
                    (16 + (30 - 16) * collapsingToolbarScaffoldState.toolbarState.progress).sp
                val startMargin =
                    (60 + (16 - 60) * collapsingToolbarScaffoldState.toolbarState.progress).dp
                val bottomMargin = (16 * collapsingToolbarScaffoldState.toolbarState.progress).dp

                val animatedToolbarContentColor by animateColorAsState(
                    lerp(
                        MaterialTheme.colors.onSurface,
                        Color.White,
                        collapsingToolbarScaffoldState.toolbarState.progress
                    )
                )

                InsetTopBar(
                    modifier = Modifier.pin(),
                    backgroundColor = Color.Transparent,
                    contentColor = animatedToolbarContentColor,
                    navigationIcon = { TopAppBarBackButton(contentColor = animatedToolbarContentColor) },
                    enableDivider = false
                )

                state.featured?.let {
                    Column(
                        modifier = Modifier
                            .road(Alignment.CenterStart, Alignment.BottomStart)
                            .padding(start = startMargin, end = 16.dp, bottom = bottomMargin)
                    ) {
                        Spacer(modifier = Modifier.statusBarsPadding())
                        Box(modifier = Modifier.defaultMinSize(minHeight = 60.dp)) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = it.title,
                                style = MaterialTheme.typography.h2,
                                fontSize = titleSize,
                                color = animatedToolbarContentColor
                            )
                        }
                    }
                }
            },
            body = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                        .verticalScroll(rememberScrollState())
                ) {

                    Crossfade(!state.isContentRefreshing) { visible ->
                        if (visible) {
                            Text(
                                modifier = Modifier.padding(20.dp),
                                text = state.featured!!.content.trimIndent(),
                                style = MaterialTheme.typography.body2.copy(
                                    lineHeight = 25.sp,
                                    letterSpacing = 1.sp
                                )
                            )
                        } else {
                            FeaturedDetailScreenContentShimmer()
                        }
                    }

                    AnimatedVisibility(!state.isContentRefreshing) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Header("관련된 코스들")
                            CourseHorizontalList(
                                list = state.courses,
                                onCourseClicked = onCourseClicked
                            )
                        }
                    }

                    Spacer(modifier = Modifier.navigationBarsPadding())
                    Spacer(modifier = Modifier.height(100.dp))

                }
            }
        )
    }
}

@Composable
private fun FeaturedDetailScreenContentShimmer() {
    Column {
        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(
                modifier = Modifier
                    .width(200.dp)
                    .height(18.dp)
                    .shimmer()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(
                modifier = Modifier
                    .width(150.dp)
                    .height(18.dp)
                    .shimmer()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(
                modifier = Modifier
                    .width(300.dp)
                    .height(18.dp)
                    .shimmer()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Spacer(
                modifier = Modifier
                    .width(100.dp)
                    .height(18.dp)
                    .shimmer()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.horizontalScroll(state = rememberScrollState(), enabled = false)
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Spacer(
                modifier = Modifier
                    .size(200.dp, 300.dp)
                    .shimmer(shape = RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.width(20.dp))
            Spacer(
                modifier = Modifier
                    .size(200.dp, 300.dp)
                    .shimmer(shape = RoundedCornerShape(20.dp))
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}