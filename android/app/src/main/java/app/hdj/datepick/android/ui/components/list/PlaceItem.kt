package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.preview.FakePlaceProvider
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*
import app.hdj.shared.client.domain.entity.Place

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaceItem(place: Place?, onClick: (Place) -> Unit) {

    Surface(
        modifier = Modifier,
        color = Color.Transparent,
        onClick = { place?.let { onClick(it) } }
    ) {

        ConstraintLayout(
            modifier = Modifier.padding(20.dp),
        ) {
            val (imageRef, titleRef, subtitleRef, pickButtonRef) = createRefs()

            Image(
                painter = rememberUrlImagePainter(place?.photoUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .clip(MaterialTheme.shapes.small)
                    .constrainAs(
                        imageRef,
                        t2t() + match()
                    ),
            )

            if (place != null) {

                Text(
                    text = place.name,
                    modifier = Modifier
                        .constrainAs(
                            titleRef,
                            t2b(imageRef, margin = 10.dp) + s2s(imageRef)
                        )
                )

            } else {

            }

        }

    }

}

@Composable
@Preview
fun PlaceItemPreview(
    @PreviewParameter(FakePlaceProvider::class) place: Place
) {
    DatePickTheme {
        PlaceItem(place = place) {

        }
    }
}