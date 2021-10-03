package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.providers.preview.FakeFeaturedPreviewProvider
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.utils.*

@Composable
fun FeaturedPagerItem(
    featured: Featured,
    onFeaturedClicked: (Featured) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Card(
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
                        style = MaterialTheme.typography.h1,
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
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )

                }

            }
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