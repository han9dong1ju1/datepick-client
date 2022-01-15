package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.diary.Diary
import app.hdj.datepick.ui.components.NetworkImage

@Composable
fun DiaryListItem(
    diary : Diary,
    onDiaryClicked: (Diary) -> Unit
) {
    Surface(
        onClick = { onDiaryClicked(diary) },
        shape = RoundedCornerShape(10.dp)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NetworkImage(
                url = "https://picsum.photos/200/300",
                modifier = Modifier
                    .size(150.dp, 180.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "안국동 데이트",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "2021. 11. 1",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )

        }
    }
}