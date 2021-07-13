package app.hdj.datepick.android.ui.components.list.place

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.utils.*
import app.hdj.shared.client.domain.entity.Place
import coil.size.Scale
import coil.transform.RoundedCornersTransformation

@Composable
fun PlaceCardItem(
    place: Place,
    onPlaceClicked: (Place) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.medium)
        .clickable { onPlaceClicked(place) }
    ) {

        val loadPainter = rememberUrlImagePainter(
            place.photoUrl,
            requestBuilder = {
                scale(Scale.FILL)
                transformations(RoundedCornersTransformation(15.dp.value))
            }
        )

        NetworkImage(
            modifier = Modifier
                .size(150.dp)
                .clip(MaterialTheme.shapes.medium),
            loadPainter = loadPainter,
            onLoading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
                )
            }
        )


        VerticalMargin(10.dp)

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = place.name, style = MaterialTheme.typography.h4)
            VerticalMargin(4.dp)
            Text(text = place.type, style = MaterialTheme.typography.body2)
            VerticalMargin(10.dp)
        }

    }
}


@Composable
fun PlaceItem(
    leftComponent: (@Composable BoxScope.() -> Unit)? = null,
    place: Place,
    onPlaceClicked: (Place) -> Unit
) {

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .clickable {
            onPlaceClicked(place)
        }
    ) {

        val (leftCompRef, imageRef, textsRef) = createRefs()

        Box(
            modifier = Modifier.constrainAs(
                leftCompRef,
                s2s(margin = 20.dp) + t2t() + b2b()
            )
        ) {
            leftComponent?.invoke(this)
        }

        val loadPainter = rememberUrlImagePainter(
            place.photoUrl,
            requestBuilder = {
                scale(Scale.FILL)
                transformations(RoundedCornersTransformation(10.dp.value))
            }
        )

        NetworkImage(
            modifier = Modifier
                .size(56.dp)
                .clip(MaterialTheme.shapes.small)
                .constrainAs(
                    imageRef,
                    t2t(leftCompRef) + b2b(leftCompRef) + s2e(leftCompRef, margin = 20.dp)
                ),
            loadPainter = loadPainter,
            onLoading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
                )
            }
        )

        Column(
            modifier = Modifier
                .constrainAs(
                    textsRef,
                    t2t(leftCompRef) + b2b(leftCompRef) + s2e(imageRef, margin = 20.dp)
                )
        ) {

            Text(text = place.name, style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = place.type, style = MaterialTheme.typography.body2)
        }

    }

}

@Composable
fun RankedPlaceItem(rank: Int, place: Place, onPlaceClicked: (Place) -> Unit) {
    PlaceItem(
        leftComponent = {
            Text(
                modifier = Modifier
                    .width(20.dp)
                    .align(Alignment.CenterStart),
                text = rank.toString(), style = MaterialTheme.typography.h5
            )
        },
        place,
        onPlaceClicked = onPlaceClicked
    )
}
