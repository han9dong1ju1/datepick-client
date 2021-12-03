package app.hdj.datepick.android.ui.screens.others.course

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetSmallTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun CourseScreen(
    courseId: Long? = null,
    vm: CourseViewModelDelegate = hiltViewModel<CourseViewModel>()
) {

    val (state, effect, event) = vm.extract()


    val scrollBehavior = remember {
        TopAppBarDefaults.pinnedScrollBehavior()
    }

    SideEffect {

    }

    var title by remember {
        mutableStateOf("")
    }

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetSmallTopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                actions = {
                    IconButton(onClick = {
                        event(CourseViewModelDelegate.Event.ToggleEditMode)
                    }) {
                        AnimatedContent(targetState = state.isEditMode) {
                            Icon(
                                imageVector = if (it) Icons.Rounded.Edit else Icons.Rounded.Done,
                                contentDescription = null
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn {

            item {

                Box(modifier = Modifier.padding(20.dp)) {

                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title,
                        readOnly = !state.isEditMode,
                        onValueChange = { title = it },
                        textStyle = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary)
                    )

                }
            }

        }
    }

}

//@Composable
//@Preview
//fun CourseScreenPreview() {
//    BaseTheme {
//        CourseScreen(vm = fakeCourseViewModel())
//    }
//}