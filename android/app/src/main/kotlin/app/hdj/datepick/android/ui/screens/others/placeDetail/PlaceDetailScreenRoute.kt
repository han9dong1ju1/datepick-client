package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail.ARGUMENT_PLACE
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail.ARGUMENT_PLACE_ID
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.utils.getJsonDataArgument
import kotlinx.serialization.Serializable

@Serializable
data class PlaceCategoryNavigationArgument(
    override val category: String,
    override val type: String,
    override val subtype: String
) : Place.Category

@Serializable
data class PlaceNavigationArgument(
    override val id: Long,
    override val kakaoId: Long,
    override val name: String,
    override val category: PlaceCategoryNavigationArgument,
    override val address: String,
    override val latitude: Double,
    override val longitude: Double,
    override val rating: Double,
    override val isPicked: Boolean,
    override val photo : String?,
) : Place {
    companion object {
        fun fromPlace(place: Place) = with(place) {
            PlaceNavigationArgument(
                id,
                kakaoId,
                name,
                PlaceCategoryNavigationArgument(category.category, category.type, category.subtype),
                address,
                latitude,
                longitude,
                rating,
                isPicked,
                photo
            )
        }
    }
}

fun NavGraphBuilder.placeDetailScreen() {
    appNavigationComposable(PlaceDetail) {

        val place = it.getJsonDataArgument<PlaceNavigationArgument>(ARGUMENT_PLACE)

        val placeId =
            it.arguments?.getLong(ARGUMENT_PLACE_ID)

        PlaceDetailScreen(placeId, place)
    }
}
