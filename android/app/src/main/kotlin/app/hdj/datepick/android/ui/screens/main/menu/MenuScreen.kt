package app.hdj.datepick.android.ui.screens.main.menu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.badge.DatePickBadges
import app.hdj.datepick.android.ui.icons.*
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.providers.preview.FakeUserPreviewProvider
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import coil.transform.CircleCropTransformation

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun MenuScreen(
    vm: MenuViewModelDelegate = hiltViewModel<MenuViewModel>()
) {
    val (state, effect, event) = vm.extract()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    val navController = LocalAppNavController.current

    val me = LocalMe.current

    BaseScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetLargeTopAppBar(
                title = { Text(text = "전체") },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            if (me == null) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(horizontal = 20.dp))
                ) {

                    Button(onClick = {
                        navController.navigateRoute(AppNavigationGraph.LoginDialog)
                    }) {
                        Text(text = "로그인하기")
                    }

                }

            } else {

                ListItem(
                    title = me.nickname,
                    subtitle = "내 프로필 설정",
                    leftSideUi = {
                        NetworkImage(
                            modifier = Modifier.size(60.dp),
                            url = me.profileImage,
                            imageRequestBuilder = { transformations(CircleCropTransformation()) },
                            onFailed = {
                                Surface(
                                    modifier = Modifier.size(60.dp),
                                    tonalElevation = 10.dp,
                                    shape = CircleShape
                                ) {
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        Image(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(20.dp)
                                                .align(Alignment.Center),
                                            imageVector = Icons.Rounded.Person,
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) {
                    navController.navigateRoute(AppNavigationGraph.UserProfileEdit)
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
            )

            Header("내 계정")

            ListItem(
                title = "내 데이트 다이어리",
                leftSideUi = {
                    Icon(
                        imageVector = DatePickIcons.Diary,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                rightSideUi = DatePickBadges.event
            ) {

            }

            Header("설정")

            ListItem(
                title = "알림 설정",
                leftSideUi = {
                    Icon(
                        imageVector = DatePickIcons.NotificationSettings,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                rightSideUi = DatePickBadges.new
            ) {

            }
            ListItem(
                title = "앱 설정",
                leftSideUi = {
                    Icon(
                        imageVector = DatePickIcons.AppSettings,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                rightSideUi = DatePickBadges.new
            ) {
                navController.navigateRoute(AppNavigationGraph.AppSettings)
            }

            Header("기타")

            ListItem(
                title = "공지사항",
                leftSideUi = {
                    Icon(imageVector = DatePickIcons.Notice, contentDescription = null,
                        tint = Color.Unspecified)
                },
                rightSideUi = DatePickBadges.notice
            ) {

            }

            ListItem(
                title = "오픈센스 라이센스",
                leftSideUi = {
                    Icon(
                        imageVector = Icons.Rounded.Code,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                },
                rightSideUi = DatePickBadges.update
            ) {

            }

        }


    }

}