package app.hdj.datepick.android.ui.screens.others.placeDetail

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail.ARGUMENT_PLACE
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail.ARGUMENT_PLACE_ID
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.utils.getArgument
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceNavigationArgument(
    override val id: Long,
    override val kakaoId: Long,
    override val name: String,
    override val address: String,
    override val latitude: Double,
    override val longitude: Double,
    override val rating: Double,
    override val isPicked: Boolean,
    override val photos: List<String>
) : Place, Parcelable {
    companion object {
        fun fromPlace(place: Place) = with(place) {
            PlaceNavigationArgument(
                id, kakaoId, name, address, latitude, longitude, rating, isPicked, photos
            )
        }
    }
}

fun NavGraphBuilder.placeDetailScreen() {
    appNavigationComposable(PlaceDetail) {
        val navController = LocalAppNavController.current

        val place = navController.getArgument<PlaceNavigationArgument>(ARGUMENT_PLACE)

        val placeId =
            it.arguments?.getLong(ARGUMENT_PLACE_ID)

        PlaceDetailScreen(placeId, place)
    }
}
