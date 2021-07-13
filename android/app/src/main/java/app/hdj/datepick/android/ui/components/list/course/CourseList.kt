package app.hdj.datepick.android.ui.components.list.course

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.utils.*
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course

fun LazyListScope.courseRowList(
    stateData: StateData<List<Course>>,
    onCourseClicked: (Course) -> Unit
) {
    item {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            item { Spacer(modifier = Modifier.width(20.dp)) }

            statefulItems(
                stateData,
                error = {},
                loading = { CourseRowLoading() }
            ) { _, item ->
                Row {
                    CourseRowItem(item, onCourseClicked)
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}