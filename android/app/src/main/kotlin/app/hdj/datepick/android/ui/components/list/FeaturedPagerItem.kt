package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.providers.preview.FakeFeaturedPreviewProvider
import app.hdj.datepick.android.ui.shimmer
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.utils.*

@Composable
fun FeaturedPagerItem(
    featured: Featured,
    onFeaturedClicked: (Featured) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        onClick = { onFeaturedClicked(featured) }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            NetworkImage(
                modifier = Modifier.fillMaxSize(),
                url = featured.photoUrl
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                val (titleRef, descriptionRef) = createRefs()

                Text(
                    modifier = Modifier.constrainAs(
                        titleRef,
                        s2s(descriptionRef) + b2t(descriptionRef, 8.dp)
                    ),
                    text = featured.title,
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(
                            descriptionRef,
                            s2s() + b2b()
                        ),
                    text = featured.description,
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )

            }

        }
    }

}

@Composable
fun FeaturedPagerItemShimmer() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground.copy(0.05f))
            .height(450.dp),
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)
                    .shimmer(
                        shape = RoundedCornerShape(20.dp),
                        alpha = 0.05f
                    )
            )

            Spacer(Modifier.height(10.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .shimmer(
                        shape = RoundedCornerShape(20.dp),
                        alpha = 0.05f
                    )
            )

            Spacer(Modifier.height(10.dp))

            Spacer(
                modifier = Modifier
                    .width(100.dp)
                    .height(16.dp)
                    .shimmer(
                        shape = RoundedCornerShape(20.dp),
                        alpha = 0.05f
                    )
            )

        }
    }

}

@Preview
@Composable
fun _FeaturedPagerItemPreview(
    @PreviewParameter(provider = FakeFeaturedPreviewProvider::class) featured: List<Featured>
) {
    PreviewScope {
        FeaturedPagerItem(featured.first(), {})
    }
}