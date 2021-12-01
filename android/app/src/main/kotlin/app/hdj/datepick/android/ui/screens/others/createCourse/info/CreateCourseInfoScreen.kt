package app.hdj.datepick.android.ui.screens.others.createCourse.info

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.screens.others.createCourse.CreateCourseViewModelDelegate


@Composable
fun CreateCourseInfoScreen(
    state: CreateCourseViewModelDelegate.State,
    event: (CreateCourseViewModelDelegate.Event) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "추천 장소들을\n선택해보세요.",
            style = MaterialTheme.typography.headlineMedium
        )

    }


}