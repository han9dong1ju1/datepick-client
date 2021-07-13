package app.hdj.datepick.android.ui.components.list.course

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.ShimmerAnimation
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import app.hdj.shared.client.domain.entity.Course
import coil.size.Scale
import coil.transform.RoundedCornersTransformation

internal val COURSE_ITEM_HEIGHT = 300.dp
internal val COURSE_ITEM_WIDTH = 240.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseRowItem(course: Course, onCourseClicked: (Course) -> Unit) {

    Card(
        elevation = 0.dp,
        modifier = Modifier.size(COURSE_ITEM_WIDTH, COURSE_ITEM_HEIGHT),
        onClick = { onCourseClicked(course) },
        content = {

            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    painter = rememberUrlImagePainter(
                        course.photoUrl,
                        requestBuilder = {
                            scale(Scale.FILL)
                            transformations(RoundedCornersTransformation(15.dp.value))
                        }),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.BottomStart)
                ) {

                    Text(
                        text = course.name,
                        maxLines = 2,
                        color = Color.White,
                        style = MaterialTheme.typography.h5,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = course.categories.joinToString(separator = " ") { "#$it" },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = Color.White,
                        style = MaterialTheme.typography.body2
                    )

                }

            }


        }
    )

}

@Composable
fun CourseRowLoading() {

    Row {
        (0..3).forEach { _ ->
            Box(
                modifier = Modifier
                    .size(COURSE_ITEM_WIDTH, COURSE_ITEM_HEIGHT)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                ShimmerAnimation()
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }

}
