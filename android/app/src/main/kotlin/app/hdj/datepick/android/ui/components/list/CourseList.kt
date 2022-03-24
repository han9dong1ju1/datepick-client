package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.ui.components.NetworkImage
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun CourseHorizontalListItem(
    course: Course,
    onClick: (Course) -> Unit
) {

    Surface(
        onClick = { onClick(course) },
        modifier = Modifier.size(200.dp, 300.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            NetworkImage(
                url = course.imageUrl,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier.fillMaxSize().background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(0.2f),
                            Color.Black.copy(0.3f),
                            Color.Black.copy(0.7f),
                            Color.Black.copy(0.9f)
                        )
                    )
                )
            )

            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = course.title,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(6.dp))

                course.meetAtInstant?.toLocalDateTime(TimeZone.currentSystemDefault())?.run {
                    Text(
                        "${year}년 ${monthNumber}월 ${dayOfMonth}일",
                        color = Color.White.copy(0.5f),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    NetworkImage(
                        modifier = Modifier.size(14.dp).clip(CircleShape)
                            .border(BorderStroke(1.dp, Color.White.copy(0.5f)), shape = CircleShape),
                        url = course.user.imageUrl,
                        onFailed = {
                            Icon(
                                modifier = Modifier.size(14.dp),
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    )

                    Spacer(Modifier.width(10.dp))

                    Text(
                        text = course.user.nickname,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    color = Color.White.copy(0.5f),
                    style = MaterialTheme.typography.body2,
                    text = course.tags.joinToString(separator = ", ") { it.name },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )


                Spacer(modifier = Modifier.height(20.dp))

            }

        }

    }

}