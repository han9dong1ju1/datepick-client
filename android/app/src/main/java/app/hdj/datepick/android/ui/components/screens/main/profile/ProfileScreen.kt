package app.hdj.datepick.android.ui.components.screens.main.profile

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.dialog.login.LoginDialog
import app.hdj.datepick.android.ui.preview.FakeUserStateProvider
import app.hdj.datepick.android.ui.providers.LocalMeState
import app.hdj.datepick.android.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.LargeTitle
import app.hdj.datepick.ui.components.rememberDialogState
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import timber.log.Timber

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfileScreen(
    vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>(),
    onSettingClicked: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()
    val meState = LocalMeState.current

    val loginDialogState = rememberDialogState(initialState = false)

    val lazyListState = rememberLazyListState()

    val visibleIndex = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0

    lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.let {
        Timber.d(it.index.toString())
    }

    val coroutineScope = rememberCoroutineScope()

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DatePickTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                    },
                title = {
                    AnimatedVisibility(
                        visible = visibleIndex != 0,
                        enter = fadeIn() + slideInVertically({ it / 2 }),
                        exit = fadeOut() + slideOutVertically({ it / 2 })
                    ) {
                        Text(text = "프로필")
                    }
                },
                actions = {
                    IconButton(onClick = { onSettingClicked() }) {
                        Icon(imageVector = Icons.Rounded.Settings, null)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            state = lazyListState
        ) {
            item {
                LargeTitle(text = "프로필")
            }
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    LoginDialog(loginDialogState)

}

@Composable
@Preview
fun ProfileScreenPreview(
    @PreviewParameter(FakeUserStateProvider::class) userState: StateData<User>
) {
    DatePickTheme {
        ProvideBasicsForPreview(meState = userState) {
            ProfileScreen(fakeProfileViewModel())
        }
    }
}