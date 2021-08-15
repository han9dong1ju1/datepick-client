package app.hdj.datepick.android.ui.components.screens.main.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph.LoginDialog
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.Preview
import app.hdj.datepick.android.ui.providers.preview.FakeUserPreviewProvider
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.rememberUrlImagePainter

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>(),
    onSettingClicked: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current
    val me = LocalMe.current

    DatePickScaffold(
        Modifier.fillMaxSize(),
        topBar = {

            Column(Modifier.fillMaxWidth()) {
                DatePickTopAppBar()
                Surface(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        if (me != null) {

                            Image(
                                modifier = Modifier.size(80.dp),
                                painter = rememberUrlImagePainter(request = me.profileImageUrl),
                                contentDescription = ""
                            )

                            Spacer(Modifier.height(20.dp))

                            LargeTitle(me.nickname)

                        } else {

                            LargeTitleAndSubtitle("로그인하기", "로그인하여 더 많은 기능을 사용해보세요.")

                            Spacer(Modifier.height(20.dp))

                            DatePickCTAButton(text = "로그인") {
                                navController.navigate(LoginDialog.route)
                            }

                        }
                    }
                }
            }


        }
    ) {

        LazyColumn(Modifier.fillMaxSize()) {

        }

    }


}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ProfileScreenPreview(
    @PreviewParameter(FakeUserPreviewProvider::class) user: User?
) {
    Preview {
        CompositionLocalProvider(LocalMe provides user) {
            DatePickTheme {
                ProfileScreen(fakeProfileViewModel())
            }
        }
    }
}