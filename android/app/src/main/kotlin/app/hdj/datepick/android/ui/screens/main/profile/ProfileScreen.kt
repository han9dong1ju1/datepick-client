package app.hdj.datepick.android.ui.screens.main.profile

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.GolfCourse
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.destinations.LoginBottomSheetDialogDestination
import app.hdj.datepick.android.ui.destinations.PlaceListScreenDestination
import app.hdj.datepick.android.ui.destinations.SettingsScreenDestination
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.usecase.place.params.filterParams
import app.hdj.datepick.domain.usecase.place.params.placeQueryParams
import app.hdj.datepick.presentation.profile.ProfileScreenViewModel
import app.hdj.datepick.presentation.profile.ProfileScreenViewModelDelegate
import app.hdj.datepick.ui.components.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun ProfileScreen(
    navigator: DestinationsNavigator
) {
    ProfileScreenContent(
        onSettingClicked = { navigator.navigate(SettingsScreenDestination) },
        onLoginClicked = { navigator.navigate(LoginBottomSheetDialogDestination) },
        onPickedPlacesClicked = {
            navigator.navigate(PlaceListScreenDestination(
                placeQueryParams = placeQueryParams {
                    filterParams {

                    }
                }
            ))
        },
        onPickedCoursesClicked = { },
        vm = hiltViewModel<ProfileScreenViewModel>()
    )
}

@Composable
private fun ProfileScreenContent(
    onSettingClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {},
    onPickedPlacesClicked: () -> Unit = {},
    onPickedCoursesClicked: () -> Unit = {},
    vm: ProfileScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    BaseScaffold(
        topBar = {
            InsetTopBar(
                title = { Text("프로필") },
                actions = {
                    IconButton(onSettingClicked) {
                        Icon(imageVector = Icons.Rounded.Settings, null)
                    }
                }
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {

            item {
                ProfileBanner(state.me, onLoginClicked)
            }

            item {
                BasicListItem(
                    leftSideUi = {
                        Icon(Icons.Rounded.Place, null)
                    },
                    title = "내가 찜한 장소",
                    onClick = onPickedPlacesClicked
                )
            }

            item {
                BasicListItem(
                    leftSideUi = {
                        Icon(Icons.Rounded.GolfCourse, null)
                    },
                    title = "내가 찜한 데이트 코스",
                    onClick = onPickedCoursesClicked
                )
            }

        }

    }

}

@Composable
fun ProfileBanner(
    me: User?,
    onLoginClicked: () -> Unit
) {

    Surface(modifier = Modifier.fillMaxWidth()) {

        Crossfade(targetState = me) {
            if (it != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    NetworkImage(
                        url = it.imageUrl,
                        shape = CircleShape,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(text = it.nickname)

                }
            } else {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "로그인하기",
                        style = MaterialTheme.typography.h1
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "다양한 기능을 사용하기 위해서는 로그인이 필요해요.",
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    BaseButton(text = "로그인", onClick = onLoginClicked)

                }
            }
        }

    }

}