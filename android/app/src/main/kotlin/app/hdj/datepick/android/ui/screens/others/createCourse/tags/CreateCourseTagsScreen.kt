package app.hdj.datepick.android.ui.screens.others.createCourse.tags

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.screens.others.createCourse.CreateCourseViewModelDelegate
import app.hdj.datepick.android.ui.shimmer
import app.hdj.datepick.android.utils.foldCrossfade
import app.hdj.datepick.ui.components.Chip
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun CreateCourseTagsScreen(
    vm: CreateCourseViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "나만의 코스를\n만들어보세요.",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "아래 여러가지 태그들 중 해당되는 것을 선택해주세요.",
            color = MaterialTheme.colorScheme.onBackground
                .copy(0.5f)
                .compositeOver(MaterialTheme.colorScheme.background),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(20.dp))

        state.availableTags.foldCrossfade(
            onSuccess = {
                FlowRow {
                    it.forEach { tag ->
                        Row {
                            Chip(
                                text = tag.name,
                                isSelected = state.selectedTags.contains(tag)
                            ) {
                                event(CreateCourseViewModelDelegate.Event.SelectTag(tag))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            },
            onLoading = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    repeat(3) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shimmer(shape = RoundedCornerShape(100.dp))
                                .height(40.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        )

    }

}