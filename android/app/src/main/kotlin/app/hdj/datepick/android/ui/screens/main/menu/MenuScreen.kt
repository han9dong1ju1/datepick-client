package app.hdj.datepick.android.ui.screens.main.menu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.providers.preview.FakeUserPreviewProvider
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.statusBarsHeight

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun MenuScreen(
    vm: MenuViewModelDelegate = hiltViewModel<MenuViewModel>()
) {

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val lazyListState = rememberLazyListState()

    val me = LocalMe.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsHeight()
                ) {

                }
                LargeTopAppBar(
                    title = { Text(text = "전체") },
                    scrollBehavior = scrollBehavior
                )
            }
        }
    ) {


        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(it)) {

            if (me == null) {
                Button(onClick = {
                    navController.navigateRoute(AppNavigationGraph.LoginDialog)
                }) {
                    Text(text = "로그인하기")
                }
            } else {

                Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(28.dp)) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(20.dp)
                    ) {

                        NetworkImage(modifier = Modifier.size(60.dp), url = me.profileUrl)

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(text = me.nickname)

                    }

                }

            }

            (0..100).forEach {
                Text(text = it.toString(), modifier = Modifier.padding(20.dp))
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