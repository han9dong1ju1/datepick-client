package app.hdj.datepick.android.ui.screens.course.createCourse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModel
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate.Effect.CourseCreated
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate.Event.CreateCourse
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun CreateCourseInfoScreen(
    navigator: DestinationsNavigator
) {
    CreateCourseInfoScreenContent(
        hiltViewModel<CreateCourseScreenViewModel>()
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CreateCourseInfoScreenContent(
    vm: CreateCourseScreenViewModelDelegate
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val (state, effect, event) = vm.extract()

    effect.collectInLaunchedEffect {
        when (it) {
            is CourseCreated -> {

            }
        }
    }

    var courseName by remember { mutableStateOf("") }

    BaseScaffold(
        topBar = {
            InsetTopBar(
                navigationIcon = { TopAppBarBackButton() },
                enableDivider = false
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier.navigationBarsPadding()
            ) {
                BaseButton(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = courseName.isNotBlank(),
                    text = "완료",
                    onClick = {
                        event(CreateCourse(courseName))
                    }
                )
            }
        }
    ) {

        Column(modifier = Modifier.padding(it)) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "데이트 코스\n이름을 입력해주세요.",
                style = MaterialTheme.typography.h1
            )

            OutlinedTextField(
                value = courseName,
                onValueChange = { courseName = it },
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                label = { Text("코스 이름") },
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )

        }

    }

}