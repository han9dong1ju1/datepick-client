package app.hdj.datepick.android.ui.screens.featured

import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.shimmer
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.featured.FeaturedDetailScreenViewModel
import app.hdj.datepick.presentation.featured.FeaturedDetailScreenViewModelDelegate
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.random.Random

@Composable
fun FeaturedDetailScreen(
    featuredId: Long,
    vm: FeaturedDetailScreenViewModelDelegate = hiltViewModel<FeaturedDetailScreenViewModel>()
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

    val lazyListState = rememberLazyListState()

    val systemUiController = rememberSystemUiController()

    val swipeRefreshState = rememberSwipeRefreshState(state.isContentRefreshing)


    val isHeaderScrolled = remember(
        lazyListState.firstVisibleItemIndex == 0 &&
                lazyListState.firstVisibleItemScrollOffset > 350.dp.value.toInt()
    ) {
        if (lazyListState.firstVisibleItemIndex > 0) return@remember true
        lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset > 350.dp.value.toInt()
    }

    val showDarkForeground = remember(isHeaderScrolled, state.featured) {
        if (state.featured == null) true
        else isHeaderScrolled
    }

    val topBarBackground by animateColorAsState(
        targetValue = if (isHeaderScrolled) MaterialTheme.colors.background else Color.Transparent,
    )

    val topBarForeground by animateColorAsState(
        targetValue = if (showDarkForeground) MaterialTheme.colors.onBackground else Color.White,
    )

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

    LaunchedEffect(true) {
        event(FeaturedDetailScreenViewModelDelegate.Event.Load(featuredId))
    }

    BaseSwipeRefreshLayoutScaffold(
        indicatorPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars, additionalTop = 56.dp),
        swipeRefreshState = swipeRefreshState,
        onRefresh = {
            event(FeaturedDetailScreenViewModelDelegate.Event.Load(featuredId))
        },
        topBar = {
            BaseTopBar(
                backgroundColor = topBarBackground,
                navigationIcon = {
                    TopAppBarBackButton(contentColor = topBarForeground)
                },
                title = {

                },
                actions = {
                    if (state.featured != null) {
                        IconButton(
                            onClick = {
                                event(FeaturedDetailScreenViewModelDelegate.Event.OpenShareMenu)
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Rounded.Share,
                                    null,
                                    tint = topBarForeground
                                )
                            }
                        )
                    }
                },
                enableDivider = isHeaderScrolled
            )
        }
    ) {

        LazyColumn(
            state = lazyListState,
        ) {

            item {

                Crossfade(
                    state.featured
                ) { featured ->
                    if (featured != null) {
                        Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {

                            NetworkImage(
                                modifier = Modifier.fillMaxSize().background(color = Color.Black).alpha(0.5f),
                                url = featured.imageUrl
                            )

                            Column(
                                modifier = Modifier.padding(20.dp)
                                    .fillMaxWidth()
                                    .align(Alignment.BottomStart)
                            ) {

                                CompositionLocalProvider(LocalContentColor provides Color.White) {
                                    Text(featured.title, style = MaterialTheme.typography.h2)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        modifier = Modifier.alpha(0.8f),
                                        text = featured!!.subtitle,
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                            }

                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(400.dp)
                                .background(color = MaterialTheme.colors.surface)
                        ) {
                            Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(20.dp)) {
                                Spacer(
                                    modifier = Modifier.shimmer(color = MaterialTheme.colors.onSurface.copy(0.5f))
                                        .size(200.dp, 26.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Spacer(
                                    modifier = Modifier.shimmer(color = MaterialTheme.colors.onSurface.copy(0.5f))
                                        .size(300.dp, 14.dp)
                                )
                            }
                        }
                    }
                }


            }

            item {
                if (state.featured != null) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = state.featured!!.content.trimIndent(),
                        style = MaterialTheme.typography.body2.copy(
                            lineHeight = 25.sp,
                            letterSpacing = 1.sp
                        )
                    )
                } else {
                    Column(modifier = Modifier.padding(20.dp)) {
                        repeat(Random.nextInt(3, 5)) {
                            Spacer(
                                modifier = Modifier.width(Random.nextInt(50, 300).dp).height(14.dp).shimmer()
                            )
                            Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
                        }
                    }
                }
            }

        }

    }

}