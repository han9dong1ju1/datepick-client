package app.hdj.datepick.android.ui.screens.others.createCourse.info

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.screens.others.createCourse.CreateCourseViewModelDelegate
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.navigationBarsPadding


@Composable
fun CreateCourseInfoScreen(
    vm: CreateCourseViewModelDelegate,
    onNext: () -> Unit
) {

    val (state, effect, event) = vm.extract()

    var name by remember { mutableStateOf("") }

    effect.collectInLaunchedEffect {

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "거의 다 왔어요!",
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "아래 정보를 입력해주세요.",
                color = MaterialTheme.colors.onBackground
                    .copy(0.5f)
                    .compositeOver(MaterialTheme.colors.background),
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = "코스 이름")
                },
                value = name,
                onValueChange = { name = it }
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
                text = "만들기",
                enabled = name.isNotBlank()
            ) {
                onNext()
            }
        }
    }

}