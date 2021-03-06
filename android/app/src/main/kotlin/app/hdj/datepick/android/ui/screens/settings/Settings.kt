package app.hdj.datepick.android.ui.screens.settings

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import app.hdj.datepick.android.ui.destinations.AppThemeDialogDestination
import app.hdj.datepick.android.ui.destinations.LocationPermissionDeniedDialogDestination
import app.hdj.datepick.presentation.settings.SettingsScreenViewModelDelegate
import app.hdj.datepick.presentation.settings.SettingsScreenViewModelDelegate.Event.SetNearbyRecommendationEnable
import app.hdj.datepick.ui.utils.getActivity
import app.hdj.datepick.ui.utils.isPermissionGranted
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


data class SettingGroup(
    val header: String,
    val settings: List<SettingItem>
)

sealed class SettingItem(
    icon: ImageVector? = null,
    title: String,
    description: String? = null,
) {

    data class SwitchItem(
        val icon: ImageVector? = null,
        val title: String,
        val description: String? = null,
        val value: Boolean,
        val onSelect: () -> Unit
    ) : SettingItem(icon, title, description)

    data class NormalItem(
        val icon: ImageVector? = null,
        val title: String,
        val description: String? = null,
        val onSelect: () -> Unit
    ) : SettingItem(icon, title, description)

}

@Composable
fun rememberSettingList(
    state: SettingsScreenViewModelDelegate.State,
    event: (SettingsScreenViewModelDelegate.Event) -> Unit,
    navigator: DestinationsNavigator
): List<SettingGroup> {

    val activity = LocalContext.current.getActivity()

    val locationPermissionRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) event(SetNearbyRecommendationEnable(false))
    }

    return remember(state) {
        listOf(
            SettingGroup(
                "??????",
                listOf(
                    SettingItem.NormalItem(
                        icon = Icons.Rounded.Palette,
                        title = "??? ?????? ??????",
                        description = "?????? ????????? ???????????????.",
                    ) {
                        navigator.navigate(AppThemeDialogDestination)
                    },
                    SettingItem.NormalItem(
                        icon = Icons.Rounded.Palette,
                        title = "??? ?????? ??????",
                        description = "?????? ????????? ???????????????.",
                    ) {

                    },
                )
            ),
            SettingGroup(
                "?????? ??????",
                listOf(
                    SettingItem.SwitchItem(
                        icon = Icons.Rounded.Notifications,
                        title = "?????? ??????",
                        value = false
                    ) {
                    },
                    SettingItem.SwitchItem(
                        icon = Icons.Rounded.Event,
                        title = "????????? ??????",
                        value = false
                    ) {
                    },
                )
            ),
            SettingGroup(
                "????????? ??????",
                listOf(
                    SettingItem.SwitchItem(
                        icon = Icons.Rounded.MyLocation,
                        title = "????????? ?????? ?????? ??????",
                        description = "?????? ??? ?????? ?????? ?????? ????????? ???????????????.",
                        value = state.isNearbyRecommendEnabled
                    ) {
                        val isPermissionGranted =
                            activity!!.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)

                        if (isPermissionGranted && state.isNearbyRecommendEnabled) {
                            event(SetNearbyRecommendationEnable(false))
                        } else {
                            if (isPermissionGranted) {
                                event(SetNearbyRecommendationEnable(true))
                            } else {
                                val shouldShowRequestPermissionRationale =
                                    activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)

                                if (shouldShowRequestPermissionRationale) {
                                    navigator.navigate(LocationPermissionDeniedDialogDestination)
                                } else {
                                    locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                                }
                            }
                        }

                    },
                )
            ),
            SettingGroup(
                "??????",
                listOf(
                    SettingItem.NormalItem(
                        icon = Icons.Rounded.ContactSupport,
                        title = "????????????",
                        description = "?????? ????????? ?????? ??????????????? ????????? ???????????????.",
                    ) {

                    },
                )
            ),
            SettingGroup(
                "??????",
                listOf(
                    SettingItem.NormalItem(
                        icon = null,
                        title = "???????????? ????????????",
                        description = null,
                    ) {

                    },
                    SettingItem.NormalItem(
                        icon = null,
                        title = "???????????? ????????????",
                        description = null,
                    ) {

                    },
                    SettingItem.NormalItem(
                        icon = null,
                        title = "??? ??????",
                        description = "v.${state.appInfo.version} ${if (state.appInfo.isDebug) "- DEBUG BUILD" else ""}",
                    ) {

                    },
                )
            ),

            )
    }
}
