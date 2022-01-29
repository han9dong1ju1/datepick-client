package app.hdj.datepick.android.ui.screens.createCourse

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
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModel
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate.Effect.CourseCreated
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate.Event.CreateCourse
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateCourseInfoScreen(
    vm: CreateCourseScreenViewModelDelegate = hiltViewModel<CreateCourseScreenViewModel>()
) {

    val navController = LocalAppNavController.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val (state, effect, event) = vm.extract()

    effect.collectInLaunchedEffect {
        when (it) {
            is CourseCreated -> {
//                navController.navigateRoute(AppNavigationGraph.CourseDetailsScreen(it.courseId))
            }
        }
    }

    var courseName by remember { mutableStateOf("") }

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