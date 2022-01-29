package app.hdj.datepick.android.ui.screens.createCourse

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModel
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate
import app.hdj.datepick.ui.components.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun CreateCourseThemeScreen(
    vm: CreateCourseScreenViewModelDelegate = hiltViewModel<CreateCourseScreenViewModel>()
) {

    val navController = LocalAppNavController.current

    val (state, effect, event) = vm.extract()

    BaseScaffold(
        topBar = {
            BaseTopBar(
                navigationIcon = { TopAppBarBackButton() },
                enableDivider = false
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier.padding(
                    rememberInsetsPaddingValues(
                        insets = LocalWindowInsets.current.navigationBars,
                        applyStart = true,
                        applyEnd = true,
                        applyBottom = true,
                        additionalBottom = 20.dp,
                        additionalStart = 20.dp,
                        additionalEnd = 20.dp
                    )
                )
            ) {
                BaseButton(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = !state.selectedThemes.isNullOrEmpty(),
                    text = "다음으로",
                    onClick = { navController.navigateRoute(AppNavigationGraph.CreateCourse.Info) }
                )
            }
        }
    ) {

        Column(modifier = Modifier.padding(it)) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "데이트 코스\n테마를 정해주세요.",
                style = MaterialTheme.typography.h1
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp),
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 2.dp,
            ) {
                state.themes?.forEach { theme ->
                    BaseChip(
                        text = theme.name,
                        isSelected = theme in (state.selectedThemes ?: emptyList()),
                        onClick = {
                            event(CreateCourseScreenViewModelDelegate.Event.SelectTheme(theme))
                        },
                        textStyle = MaterialTheme.typography.subtitle2
                    )
                }
            }
        }

    }

}