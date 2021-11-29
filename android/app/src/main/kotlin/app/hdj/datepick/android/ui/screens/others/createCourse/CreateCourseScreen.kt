package app.hdj.datepick.android.ui.screens.others.createCourse

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.navigationBarsPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCourseScreen(
    courseId: String? = null,
    vm: CreateCourseViewModelDelegate = hiltViewModel<CreateCourseViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val scrollBehavior = remember {
        TopAppBarDefaults.pinnedScrollBehavior()
    }

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetSmallTopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {},
                actions = {

                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .padding(20.dp)
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
                        .compositeOver(MaterialTheme.colorScheme.background)
                )
                Spacer(modifier = Modifier.height(20.dp))
                FlowRow {
                    listOf(
                        "크리스마스", "기념일", "생일파티", "액티비티",
                        "맛있는 음식", "영화", "여행", "기념일"
                    )
                        .forEach {
                            Row {
                                Surface(
                                    onClick = {

                                    },
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = RoundedCornerShape(100.dp)
                                ) {
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 14.dp,
                                            vertical = 6.dp
                                        ), text = it
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                }
            }

            Box(
                Modifier
                    .navigationBarsPadding()
                    .align(Alignment.BottomCenter)
            ) {
                BaseButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = "다음"
                )
            }

        }

    }

}

@Composable
@Preview
fun CreateCourseScreenPreview() {
    BaseTheme {
        CreateCourseScreen(vm = fakeCreateCourseViewModel())
    }
}