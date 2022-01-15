package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.list.PlaceBlogReviewListItem
import app.hdj.datepick.android.utils.foldComposable
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.BlogReview
import app.hdj.datepick.ui.components.ListHeader

@Composable
fun PlaceDetailScreenBlogReviewSection(
    blogReviewState: LoadState<List<BlogReview>>,
    onBlogReviewClicked: (BlogReview) -> Unit
) {
    blogReviewState
        .foldComposable(
            onLoading = {
                        
            },
            onSuccess = { list ->
                ListHeader(title = "블로그 리뷰")
                list.forEach { blogReview ->
                    PlaceBlogReviewListItem(blogReview) { clickedReview ->
                        onBlogReviewClicked(clickedReview)
                    }
                }
                Spacer(Modifier.height(10.dp))
            },
            onFailed = { cached, throwable ->

            }
        )
}