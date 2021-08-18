package app.hdj.datepick.android.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.providers.Preview
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*

@Composable
fun PlaceBlogReviewListItem() {

    val title = "주차가능한 삼청동 맛집 삼청동 수제비 다녀왔어요!"
    val imageUrl = "https://picsum.photos/200"
    val content = "삼청동 맛집 삼청동 수제비 메뉴판은 이렇게 간단하구여! 아무래도 메인 메뉴인 수제비가 가장 맛있겠져?!!?…"

    Surface(onClick = { /*TODO*/ }) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            val (imageRef, titleRef, contentRef) = createRefs()

            Image(
                modifier = Modifier
                    .size(56.dp)
                    .clip(MaterialTheme.shapes.small)
                    .constrainAs(
                        imageRef,
                        t2t() + b2b() + s2s()
                    ),
                painter = rememberUrlImagePainter(request = imageUrl) {

                },
                contentDescription = null
            )

            Text(
                modifier = Modifier.constrainAs(
                    titleRef,
                    s2e(imageRef, 20.dp) + e2e() + fillWidthToConstraint + b2t(contentRef, 4.dp)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = title, style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier.constrainAs(
                    contentRef,
                    s2e(imageRef, 20.dp) + e2e() + fillWidthToConstraint + t2b(titleRef, 4.dp)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = content, style = MaterialTheme.typography.body2
            )

        }

    }

}

@Composable
@Preview(showBackground = true)
fun PlaceBlogReviewListItemPreview() {
    Preview {
        DatePickTheme {
            PlaceBlogReviewListItem()
        }
    }
}