package app.hdj.datepick.android.ui.screens.others.course

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.extract

@Composable
fun CourseScreen(
    courseId: Long? = null,
    vm: CourseViewModelDelegate = hiltViewModel<CourseViewModel>()
) {

    val (state, effect, event) = vm.extract()

    SideEffect {

    }

    var title = remember {
        mutableStateOf("")
    }

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                actions = {
                    IconButton(onClick = {
                        event(CourseViewModelDelegate.Event.ToggleEditMode)
                    }) {
                        AnimatedContent(targetState = state.isEditMode) {
                            Icon(
                                imageVector = if (it) Icons.Rounded.Done else Icons.Rounded.Edit,
                                contentDescription = null
                            )
                        }
                    }
                },
                title = {}
            )
        }
    ) {
        LazyColumn {

            item {

                Box(modifier = Modifier.padding(20.dp)) {

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