package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.NetworkImage
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

@Composable
fun MyDateListItem(
    modifier: Modifier,
    date: LocalDate,
    onClick: () -> Unit
) {

    Surface(
        onClick,
        modifier = modifier,
        color = Color.Unspecified,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = modifier.padding(10.dp)) {

            NetworkImage(
                modifier = Modifier.size(60.dp, 90.dp),
                url = null,
                onFailed = {
                    Box(
                        modifier = Modifier
                            .size(60.dp, 90.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colors.onBackground.copy(0.1f)),
                    )
                }
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text("1주년 데이트", style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    date.toString(),
                    color = MaterialTheme.colors.onBackground.copy(0.5f),
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    color = MaterialTheme.colors.onBackground.copy(0.5f),
                    style = MaterialTheme.typography.body2,
                    text = listOf("크리스마스", "기념일", "먹거리").joinToString(separator = ", "),
                )

            }
        }
    }

}