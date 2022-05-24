package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import app.hdj.datepick.android.utils.LoadStateAnimatedContent
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.components.shimmer
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun CourseHorizontalList(
    list: List<Course>,
    onCourseClicked: (Course) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        itemsIndexed(list) { index, course ->
            CourseCardListItem(
                modifier = Modifier.width(200.dp),
                course, onCourseClicked
            )
            if (list.lastIndex != index) Spacer(Modifier.width(20.dp))
        }
    }
}

@Composable
fun CourseCardListItem(
    modifier: Modifier = Modifier,
    course: Course,
    onClick: (Course) -> Unit
) {

    Surface(
        onClick = { onClick(course) },
        modifier = modifier.height(300.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            NetworkImage(
                url = course.imageUrl,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
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
                        shape = CircleShape,
                        modifier = Modifier
                            .size(14.dp)
                            .border(
                                BorderStroke(1.dp, Color.White.copy(0.5f)),
                                shape = CircleShape
                            ),
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


@Composable
fun CourseCardHeaderCarousel(
    title: String,
    recommendedCourses: LoadState<List<Course>>,
    onCourseClicked: (Course) -> Unit,
    onMoreClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Header(title, "더보기", onMoreButtonClicked = onMoreClicked)

        LoadStateAnimatedContent(loadState = recommendedCourses,
            onLoading = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState(), false)
                ) {
                    Spacer(Modifier.width(20.dp))
                    Box(
                        modifier = Modifier
                            .size(200.dp, 300.dp)
                            .shimmer(shape = RoundedCornerShape(20.dp))
                    )
                }
            },
            onSuccess = {
                CourseHorizontalList(it, onCourseClicked)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun CourseVerticalListItem(
    course: Course,
    onCourseClicked: (Course) -> Unit
) {
    app.hdj.datepick.ui.components.BasicListItem(
        title = course.title,
        subtitle = course.tags.joinToString { it.name },
        rightSideUi = course.imageUrl?.let {
            {
                NetworkImage(
                    url = it,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
            }
        },
        onClick = { onCourseClicked(course) }
    )
}
