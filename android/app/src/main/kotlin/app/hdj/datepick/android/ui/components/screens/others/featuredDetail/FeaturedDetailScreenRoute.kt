package app.hdj.datepick.android.ui.components.screens.others.featuredDetail

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import androidx.navigation.navDeepLink
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.utils.datePickNavDeepLink
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.utils.getArgument
import kotlinx.parcelize.Parcelize
import com.google.accompanist.navigation.animation.composable

@Parcelize
data class FeaturedNavigationArgument(
    override val id: Long,
    override val title: String,
    override val description: String,
    override val photoUrl: String
) : Featured, Parcelable

fun NavGraphBuilder.featuredDetailScreen() {
    composable(
        AppNavigationGraph.FeaturedDetail.route,
        deepLinks = listOf(datePickNavDeepLink(AppNavigationGraph.FeaturedDetail.route)),
        arguments = listOf(
            navArgument(AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED_ID) {
                type = NavType.LongType
            }
        )
    ) {
        val navController = LocalAppNavController.current

        val id = it.arguments?.getLong(AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED_ID)

        val featured = navController
            .getArgument<FeaturedNavigationArgument>(AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED)

        FeaturedDetailScreen(
            id = id,
            prevFeatured = featured,
        )
    }
}