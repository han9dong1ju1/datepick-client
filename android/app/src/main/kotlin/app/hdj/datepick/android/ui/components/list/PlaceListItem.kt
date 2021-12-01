package app.hdj.datepick.android.ui.components.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.shimmer
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.ImmutableRatingBar
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.utils.*

@Composable
fun PlaceVerticalListItem(place: Place, onPlaceClicked: (Place) -> Unit) {

    Surface(onClick = { onPlaceClicked(place) }) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            val (textsRef, imageRef) = createRefs()

            Column(
                modifier = Modifier.constrainAs(
                    textsRef,
                    t2t() + s2s() + b2b() + e2s(imageRef, 20.dp) + fillWidthToConstraint
                )
            ) {
                Text(
                    text = place.category.typeAsString,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.3f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = place.name, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = place.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                ImmutableRatingBar(starSize = 12.dp, rating = place.rating.toFloat())
            }

            if (place.photo != null) {

                NetworkImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .constrainAs(
                            imageRef,
                            t2t() + b2b() + e2e()
                        ),
                    url = place.photo,
                )

            }

        }

    }

}


@Composable
fun PlaceHorizontalListItemShimmer() {

    Surface {
        Column(
            modifier = Modifier
                .width(200.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .shimmer(shape = RoundedCornerShape(10.dp))
            ) {
            }

            Spacer(modifier = Modifier.height(20.dp))
            Spacer(
                modifier = Modifier
                    .shimmer()
                    .height(20.dp)
                    .width(80.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(
                modifier = Modifier
                    .shimmer()
                    .height(20.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun PlaceHorizontalListItem(
    place: Place,
    isSelected: Boolean = false,
    onPlaceClicked: (Place) -> Unit
) {

    Surface(onClick = { onPlaceClicked(place) }) {
        Column(
            modifier = Modifier
                .width(200.dp)
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                NetworkImage(
                    modifier = Modifier.fillMaxSize(),
                    url = place.photo,
                )

                Crossfade(isSelected) {
                    if (it) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.secondaryContainer.copy(
                                        0.8f
                                    )
                                )
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.Center),
                                imageVector = Icons.Rounded.Done,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                contentDescription = null
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = place.category.typeAsString,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(0.3f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = place.name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = place.address,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ImmutableRatingBar(starSize = 12.dp, rating = place.rating.toFloat())
        }
    }
}