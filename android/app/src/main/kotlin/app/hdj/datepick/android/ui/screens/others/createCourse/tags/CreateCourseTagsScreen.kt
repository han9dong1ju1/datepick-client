package app.hdj.datepick.android.ui.screens.others.createCourse.tags

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.screens.others.createCourse.CreateCourseViewModelDelegate
import app.hdj.datepick.android.ui.shimmer
import app.hdj.datepick.android.utils.foldCrossfade
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.Chip
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.navigationBarsPadding


@Composable
fun CreateCourseTagsScreen(
    vm: CreateCourseViewModelDelegate,
    onNext: () -> Unit
) {

    val (state, effect, event) = vm.extract()

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "나만의 코스를\n만들어보세요.",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "아래 여러가지 태그들 중 해당되는 것을 선택해주세요.",
                color = MaterialTheme.colors.onBackground
                    .copy(0.5f)
                    .compositeOver(MaterialTheme.colors.background),
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(20.dp))

            state.availableTags.foldCrossfade(
                onSuccess = {
                    FlowRow(crossAxisSpacing = 12.dp, mainAxisSpacing = 12.dp) {
                        it.forEach { tag ->
                            Chip(
                                modifier = Modifier.height(40.dp),
                                text = tag.name,
                                isSelected = state.selectedTags.contains(tag)
                            ) {
                                event(CreateCourseViewModelDelegate.Event.SelectTag(tag))
                            }
                        }
                    }
                },
                onLoading = {
                    FlowRow(crossAxisSpacing = 12.dp, mainAxisSpacing = 12.dp) {
                        repeat(10) {
                            Spacer(
                                modifier = Modifier
                                    .width((50..100).random().dp)
                                    .shimmer(shape = RoundedCornerShape(10.dp))
                                    .height(40.dp)
                            )
                        }
                    }
                }
            )

        }

        Box(
            Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BaseButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = if (state.selectedTags.isEmpty()) "건너뛰기" else "다음으로"
            ) {
                onNext()
            }
        }
    }

}