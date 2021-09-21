package app.hdj.datepick.android.ui.screens.main.menu

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.ProfileImage
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.providers.preview.FakeUserPreviewProvider
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.ui.animation.materialTransitionYaxisIn
import app.hdj.datepick.ui.animation.materialTransitionYaxisOut
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.isFirstItemScrolled
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(
    vm: MenuViewModelDelegate = hiltViewModel<MenuViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val lazyListState = rememberLazyListState()

    val isHeaderScrolled = lazyListState.isFirstItemScrolled(100.dp)

//    val me = LocalMe.current
    val me = FakeUserPreviewProvider().values.first()

    DatePickScaffold(
        Modifier.fillMaxSize(),
        topBar = {
            val elevationAnimate by animateDpAsState(targetValue = if (isHeaderScrolled) AppBarDefaults.TopAppBarElevation else 0.dp)

            DatePickTopAppBar(
                elevation = elevationAnimate,
                navigationIcon = {
                    Spacer(modifier = Modifier.width(16.dp))
                    AnimatedVisibility(
                        isHeaderScrolled,
                        enter = materialTransitionZaxisIn,
                        exit = materialTransitionZaxisOut
                    ) {
                        ProfileImage(
                            modifier = Modifier.size(24.dp),
                            profileImageUrl = me?.profileImageUrl
                        )
                    }
                },
                title = {
                    AnimatedVisibility(
                        isHeaderScrolled,
                        enter = materialTransitionYaxisIn,
                        exit = materialTransitionYaxisOut
                    ) {
                        Text(text = "전체 메뉴")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigateRoute(AppNavigationGraph.Settings.GeneralSettings)
                    }) {
                        Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
                    }
                }
            )
        }
    ) {

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it),
            state = lazyListState,
        ) {

            item { LargeTitle(text = "전체 메뉴") }

            item { MenuScreenHeader(me) }

            items(100) {
                Text(
                    text = "$it", modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
            }

        }

    }


}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ProfileScreenPreview(
    @PreviewParameter(FakeUserPreviewProvider::class) user: User?
) {
    PreviewScope {
        CompositionLocalProvider(LocalMe provides user) {
            DatePickTheme {
                MenuScreen(fakeMenuViewModel())
            }
        }
    }
}