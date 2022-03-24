package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.ui.components.NetworkImage
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun MyDateListItem(
    modifier: Modifier,
    course: Course,
    onClick: (Course) -> Unit
) {

    Surface(
        { onClick(course) },
        modifier = modifier,
        color = Color.Unspecified,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = modifier.padding(10.dp)) {

            Box(
                modifier = Modifier.size(60.dp, 90.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.surface)
            ) {
                if (course.imageUrl != null) {
                    NetworkImage(
                        modifier = Modifier.fillMaxSize(),
                        url = course.imageUrl,
                        onFailed = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colors.onBackground.copy(0.1f)),
                            )
                        }
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize().border(
                            BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(0.1f)),
                            RoundedCornerShape(10.dp)
                        ).background(MaterialTheme.colors.background)
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp).align(Alignment.Center).alpha(0.1f),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = null
                        )
                    }
                }
                if (course.isPrivate) {
                    Surface(
                        modifier = Modifier.size(30.dp).padding(4.dp).align(Alignment.BottomEnd),
                        shape = CircleShape,
                        color = Color.Black.copy(0.8f)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp),
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(course.title, style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(6.dp))
                course.meetAtInstant?.toLocalDateTime(TimeZone.currentSystemDefault())?.run {
                    Text(
                        "${monthNumber}월 ${dayOfMonth}일",
                        color = MaterialTheme.colors.onBackground.copy(0.5f),
                        style = MaterialTheme.typography.body2
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    color = MaterialTheme.colors.onBackground.copy(0.5f),
                    style = MaterialTheme.typography.body2,
                    text = course.tags.joinToString(separator = ", ") { it.name },
                )

            }
        }
    }

}