package app.hdj.datepick.android.ui.screens.others.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.StatusBarMode
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Images.ARGUMENT_IMAGES
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.getJsonDataArgument
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.pager.rememberPagerState
import kotlinx.serialization.Serializable

@Serializable
data class ImagesScreenArgument(
    val title: String,
    val images: List<String>,
    val selectedIndex: Int
)

fun NavGraphBuilder.imagesScreen() {

    appNavigationComposable(AppNavigationGraph.Images) {

        val images =
            it.getJsonDataArgument<ImagesScreenArgument>(ARGUMENT_IMAGES)

        images?.let { ImagesScreen(imagesArg = it) }
    }

}

@Composable
fun ImagesScreen(imagesArg: ImagesScreenArgument) {

    val pagerState = rememberPagerState(
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

    BaseScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        topBar = {
            SmallTopAppBar(
                title = { Text(imagesArg.title, color = Color.White) },
                navigationIcon = { TopAppBarBackButton(contentColor = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            ViewPager(
                itemSpacing = 40.dp,
                modifier = Modifier.fillMaxSize(),
                list = imagesArg.images,
                pagerState = pagerState
            ) { item, position ->
                Image(
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    painter = rememberImagePainter(data = item),
                    contentDescription = null
                )
            }

            Text(
                modifier = Modifier
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding(bottom = true),
                text = "${pagerState.currentPage + 1} / ${pagerState.pageCount}",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}