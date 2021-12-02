package app.hdj.datepick.android.ui.screens.others.createCourse.info

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.screens.others.createCourse.CreateCourseViewModelDelegate
import app.hdj.datepick.ui.utils.extract


@Composable
fun CreateCourseInfoScreen(
    vm: CreateCourseViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "이 코스의 이름을\n입력해주세요.",
            style = MaterialTheme.typography.headlineMedium
        )

    }


}