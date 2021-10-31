package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.providers.preview.FakePlaceBlogReviewPreviewProvider
import app.hdj.datepick.android.utils.loadNaverBlogUrlPreviewImage
import app.hdj.datepick.domain.fold
import app.hdj.datepick.domain.model.place.BlogReview
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.*

@Composable
fun PlaceBlogReviewListItem(blogReview: BlogReview, onBlogReviewClicked: (BlogReview) -> Unit) {

    val imageUrl by loadNaverBlogUrlPreviewImage(blogReview.url)

    Surface(onClick = { onBlogReviewClicked(blogReview) }) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            val (imageRef, titleRef, contentRef) = createRefs()

            NetworkImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .constrainAs(
                        imageRef,
                        t2t() + b2b() + s2s()
                    ),
                url = imageUrl.fold(onSuccess = { it })
            )

            Text(
                modifier = Modifier.constrainAs(
                    titleRef,
                    s2e(imageRef, 20.dp) + e2e() + fillWidthToConstraint + b2t(contentRef, 2.dp)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = blogReview.title, style = MaterialTheme.typography.bodyMedium
            )

            Text(
                modifier = Modifier.constrainAs(
                    contentRef,
                    s2e(imageRef, 20.dp) + e2e() + fillWidthToConstraint + t2b(titleRef, 2.dp)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = blogReview.content, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                    .compositeOver(MaterialTheme.colorScheme.surface)
            )

        }

    }

}

@Composable
@Preview(showBackground = true)
fun PlaceBlogReviewListItemPreview(
    @PreviewParameter(FakePlaceBlogReviewPreviewProvider::class) blogReviews: List<BlogReview>
) {
    PreviewScope {
        BaseTheme {
            PlaceBlogReviewListItem(blogReviews.first()) {}
        }
    }
}