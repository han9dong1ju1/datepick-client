package app.hdj.datepick.android.ui.screens.others.image

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.DatePickAppViewModel
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.StatusBarMode
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Images.ARGUMENT_IMAGES
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.ui.components.DatePickPager
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.getArgument
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.rememberPagerState
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import kotlin.math.roundToInt

@Parcelize
data class ImagesScreenArgument(
    val images: List<String>,
    val selectedIndex: Int
) : Parcelable

fun NavGraphBuilder.imagesScreen() {

    appNavigationComposable(AppNavigationGraph.Images) {

        val navController = LocalAppNavController.current

        val images =
            navController.getArgument<ImagesScreenArgument>(ARGUMENT_IMAGES)

        images?.let { ImagesScreen(imagesArg = it) }
    }

}

@Composable
fun ImagesScreen(imagesArg: ImagesScreenArgument) {

    val pagerState = rememberPagerState(
        pageCount = imagesArg.images.size,
        initialPage = imagesArg.selectedIndex
    )

    val (state, effect, event) = LocalDatePickAppViewModel.current.extract()
    val previousStatusBarMode = state.statusBarMode

    DisposableEffect(true) {
        event(DatePickAppViewModelDelegate.Event.ChangeStatusBarMode(StatusBarMode.STATUS_BAR_FORCE_WHITE))
        onDispose {
            event(DatePickAppViewModelDelegate.Event.ChangeStatusBarMode(previousStatusBarMode))
        }
    }

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Black,
        topBar = {
            DatePickTopAppBar(
                navigationIcon = { TopAppBarBackButton(contentColor = Color.White) },
                backgroundColor = Color.Transparent
            )
        }
    ) {

        var scale by remember { mutableStateOf(1f) }
        var offset by remember { mutableStateOf(Offset(0f, 0f)) }

        LaunchedEffect(key1 = pagerState.currentPage) {
            scale = 1f
            offset = Offset(0f, 0f)
        }

        DatePickPager(
            itemSpacing = 40.dp,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        if (scale * zoom in 1f..5f) scale *= zoom
                        offset += pan
                    }
                },
            list = imagesArg.images,
            pagerState = pagerState
        ) { item, position ->
            Image(
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .offset {
                        IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                    }
                    .graphicsLayer(
                        scaleX = if (position == pagerState.currentPage) scale else 1f,
                        scaleY = if (position == pagerState.currentPage) scale else 1f
                    ),
                painter = rememberImagePainter(data = item),
                contentDescription = null
            )
        }

    }

}