package app.hdj.datepick.android.ui.screens.course.createCourse

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.destinations.CreateCourseInfoScreenDestination
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModel
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate
import app.hdj.datepick.ui.components.*
import com.google.accompanist.flowlayout.FlowRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun CreateCourseThemeScreen(
    navigator: DestinationsNavigator
) {
    CreateCourseThemeScreenContent(
        onNextClicked = { navigator.navigate(CreateCourseInfoScreenDestination) },
        hiltViewModel<CreateCourseScreenViewModel>()
    )
}

@Composable
private fun CreateCourseThemeScreenContent(
    onNextClicked: () -> Unit = {},
    vm: CreateCourseScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    BaseScaffold(
        topBar = {
            InsetTopBar(
                navigationIcon = { TopAppBarBackButton() },
                enableDivider = false
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(20.dp)) {
                BaseButton(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = !state.selectedThemes.isNullOrEmpty(),
                    text = "다음으로",
                    onClick = onNextClicked
                )
                Spacer(modifier = Modifier.navigationBarsPadding())
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