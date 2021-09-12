package app.hdj.datepick.android.ui.screens.others.image

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
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

        DatePickPager(
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

    }

}