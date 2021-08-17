package app.hdj.datepick.android.ui.screens.others.place

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Place.ARGUMENT_PLACE
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Place.ARGUMENT_PLACE_ID
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.utils.getArgument
import com.google.accompanist.navigation.animation.composable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceArgument(
    override val id: Long,
    override val kakaoId: Long,
    override val name: String,
    override val address: String,
    override val latitude: Double,
    override val longitude: Double,
    override val rating: Double,
    override val isPicked: Boolean,
    override val photos: List<String>
) : Place, Parcelable

fun NavGraphBuilder.placeScreen() {
    composable(
        AppNavigationGraph.Place.route, listOf(
            navArgument(ARGUMENT_PLACE_ID) {
                type = NavType.LongType
            }
        )) {
        val navController = LocalAppNavController.current

        val place = navController.getArgument<PlaceArgument>(ARGUMENT_PLACE)

        val placeId =
            it.arguments?.getLong(ARGUMENT_PLACE_ID)

        PlaceScreen(placeId, place)
    }
}
