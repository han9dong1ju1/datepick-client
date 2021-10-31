package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.ImmutableRatingBar
import app.hdj.datepick.ui.utils.*
import coil.size.Scale

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
                    color =  MaterialTheme.colorScheme.onSurface.copy(0.5f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                ImmutableRatingBar(starSize = 12.dp, rating = place.rating.toFloat())
            }

            if (place.photo != null) {

                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .constrainAs(
                            imageRef,
                            t2t() + b2b() + e2e()
                        ),
                    contentScale = ContentScale.Crop,
                    painter = rememberUrlImagePainter(request = place.photo) {
                        scale(Scale.FIT)
                    },
                    contentDescription = null
                )

            }

        }

    }

}