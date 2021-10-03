package app.hdj.datepick.android.ui.screens.others.featuredDetail

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED_ID
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.utils.getJsonDataArgument
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedNavigationArgument(
    override val id: Long,
    override val title: String,
    override val description: String,
    override val photoUrl: String
) : Featured {
    companion object {
        fun fromFeatured(featured: Featured) = with(featured) {
            FeaturedNavigationArgument(id, title, description, photoUrl)
        }
    }
}

fun NavGraphBuilder.featuredDetailScreen() {
    appNavigationComposable(FeaturedDetail) {
        val featured = it.getJsonDataArgument<FeaturedNavigationArgument>(ARGUMENT_FEATURED)
        val id = it.arguments?.getLong(ARGUMENT_FEATURED_ID)

        FeaturedDetailScreen(
            id = id,
            prevFeatured = featured,
        )
    }
}