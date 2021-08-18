package app.hdj.datepick.android.ui.screens.others.featuredDetail

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED_ID
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.argument
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.deeplink
import app.hdj.datepick.android.utils.datePickNavDeepLink
import app.hdj.datepick.android.utils.externalDatePickNavDeepLink
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
) : Featured, Parcelable {
    companion object {
        fun fromFeatured(featured: Featured) = with(featured) {
            FeaturedNavigationArgument(id, title, description, photoUrl)
        }
    }
}

fun NavGraphBuilder.featuredDetailScreen() {
    composable(FeaturedDetail.route,
        deepLinks = deeplink(),
        arguments = argument()
    ) {
        val navController = LocalAppNavController.current

        val id = it.arguments?.getLong(ARGUMENT_FEATURED_ID)

        val featured = navController
            .getArgument<FeaturedNavigationArgument>(ARGUMENT_FEATURED)

        FeaturedDetailScreen(
            id = id,
            prevFeatured = featured,
        )
    }
}