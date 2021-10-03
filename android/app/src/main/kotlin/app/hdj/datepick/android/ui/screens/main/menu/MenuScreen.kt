package app.hdj.datepick.android.ui.screens.main.menu

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.providers.preview.FakeUserPreviewProvider
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(
    vm: MenuViewModelDelegate = hiltViewModel<MenuViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val lazyListState = rememberLazyListState()

//    val me = LocalMe.current
    val me = FakeUserPreviewProvider().values.first()

   val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    BaseCollapsingToolbarScaffold(
        Modifier.fillMaxSize(),
        state = collapsingToolbarScaffoldState,
        topBar = {
            BaseCollapsingTopBar(
                modifier = Modifier.fillMaxWidth(),
                background = {
                    Surface(
                        modifier = Modifier
                            .pin()
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {}
                },
                content = {
                    val progress = collapsingToolbarScaffoldState.toolbarState.progress
                    val textSize = (16 + (24 - 16) * progress).sp
                    Text(
                        modifier = Modifier.padding(it),
                        fontSize = textSize,
                        text = "전체 메뉴"
                    )
                },
                titleWhenCollapsed = Alignment.BottomStart,
                titleWhenExpanded = Alignment.BottomStart
            )
        }
    ) {

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it),
            state = lazyListState,
        ) {

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
            BaseTheme {
                MenuScreen(fakeMenuViewModel())
            }
        }
    }
}