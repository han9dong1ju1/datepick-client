package app.hdj.datepick.android.ui.screens.others.featuredDetail

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED_ID
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.featuredDetailScreen() {
    appNavigationComposable(FeaturedDetail) {
        val id = it.arguments?.getLong(ARGUMENT_FEATURED_ID)
        FeaturedDetailScreen(id = requireNotNull(id))
    }
}