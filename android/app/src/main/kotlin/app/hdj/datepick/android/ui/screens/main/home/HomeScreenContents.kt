package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.list.FeaturedPagerItem
import app.hdj.datepick.android.ui.components.list.FeaturedPagerItemShimmer
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openFeatured
import app.hdj.datepick.android.utils.foldCrossfade
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.notice.Notice
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.ViewPager

@Composable
fun LazyItemScope.HomeScreenFeaturedContents(
    state: LoadState<List<Featured>>
) {
    val navController = LocalAppNavController.current
    state.foldCrossfade(
        modifier = Modifier.animateItemPlacement(),
        onLoading = {
            FeaturedPagerItemShimmer()
        },
        onSuccess = { featured ->
            ViewPager(
                modifier = Modifier.fillMaxWidth(),
                list = featured,
                autoScrollDelay = 7000
            ) { item, _ ->
                FeaturedPagerItem(item, navController::openFeatured)
            }
        },
        onFailed = { _, _ ->

        }
    )
}

@Composable
fun LazyItemScope.HomeScreenNoticesContents(
    state: LoadState<List<Notice>>
) {
    val navController = LocalAppNavController.current
    state.foldCrossfade(
        onSuccess = { list ->
            ViewPager(
                modifier = Modifier.fillMaxWidth(),
                list = list,
                itemSpacing = 10.dp,
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) { notice, i ->

            }
        },
        onLoading = {

        }
    )
}

@Composable
fun LazyItemScope.HomeScreenPlacesContents(
    state: LoadState<List<Place>>
) {

}

@Composable
fun LazyItemScope.HomeScreenCourseContents(
    state: LoadState<List<Course>>
) {

}