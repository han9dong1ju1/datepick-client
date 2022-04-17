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
                "일반",
                listOf(
                    SettingItem.NormalItem(
                        icon = Icons.Rounded.Palette,
                        title = "앱 테마 설정",
                        description = "앱의 테마를 설정합니다.",
                    ) {
                        navigator.navigate(AppThemeDialogDestination)
                    },
                    SettingItem.NormalItem(
                        icon = Icons.Rounded.Palette,
                        title = "앱 테마 설정",
                        description = "앱의 테마를 설정합니다.",
                    ) {

                    },
                )
            ),
            SettingGroup(
                "알림 설정",
                listOf(
                    SettingItem.SwitchItem(
                        icon = Icons.Rounded.Notifications,
                        title = "전체 알림",
                        value = false
                    ) {
                    },
                    SettingItem.SwitchItem(
                        icon = Icons.Rounded.Event,
                        title = "이벤트 알림",
                        value = false
                    ) {
                    },
                )
            ),
            SettingGroup(
                "유용한 기능",
                listOf(
                    SettingItem.SwitchItem(
                        icon = Icons.Rounded.MyLocation,
                        title = "주변에 있는 장소 보기",
                        description = "현재 내 주변 위치 기반 정보를 추천합니다.",
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
                "지원",
                listOf(
                    SettingItem.NormalItem(
                        icon = Icons.Rounded.ContactSupport,
                        title = "문의하기",
                        description = "앱의 오류나 기타 문의사항이 있다면 연락합니다.",
                    ) {

                    },
                )
            ),
            SettingGroup(
                "기타",
                listOf(
                    SettingItem.NormalItem(
                        icon = null,
                        title = "개인정보 처리방침",
                        description = null,
                    ) {

                    },
                    SettingItem.NormalItem(
                        icon = null,
                        title = "오픈소스 라이센스",
                        description = null,
                    ) {

                    },
                    SettingItem.NormalItem(
                        icon = null,
                        title = "앱 정보",
                        description = "v.${state.appInfo.version} ${if (state.appInfo.isDebug) "- DEBUG BUILD" else ""}",
                    ) {

                    },
                )
            ),

            )
    }
}
