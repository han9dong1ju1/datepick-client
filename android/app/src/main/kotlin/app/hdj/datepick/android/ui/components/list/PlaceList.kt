package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.NetworkImage

@Composable
fun PlaceHorizontalList(
    list: List<Place>,
    onPlaceClicked: (Place) -> Unit
) {
    LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 20.dp)) {
        itemsIndexed(list) { index, place ->
            PlaceHorizontalListItem(place, onPlaceClicked)
            if (list.lastIndex != index) Spacer(Modifier.width(20.dp))
        }
    }
}

@Composable
fun PlaceVertialListItem(
    place: Place,
    onPlaceClicked: (Place) -> Unit
) {
    app.hdj.datepick.ui.components.ListItem(
        title = place.name,
        subtitle = place.address,
        rightSideUi = place.imageUrl?.let {
            {
                NetworkImage(
                    url = it,
                    modifier = Modifier.size(56.dp).clip(RoundedCornerShape(8.dp)),
                )
            }
        },
        onClick = { onPlaceClicked(place) }
    )
}

@Composable
fun PlaceHorizontalListItem(
    place: Place,
    onPlaceClicked: (Place) -> Unit
) {
    Surface(
        onClick = { onPlaceClicked(place) },
        modifier = Modifier.width(200.dp),
        color = Color.Unspecified
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            NetworkImage(
                modifier = Modifier.fillMaxWidth().height(140.dp),
                shape = RoundedCornerShape(10.dp),
                url = place.imageUrl,
                onFailed = {
                    Box(
                        modifier = Modifier
                            .height(140.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                MaterialTheme.colors.onBackground.copy(0.1f)
                                    .compositeOver(MaterialTheme.colors.background)
                            )
                    ) {

                        Icon(
                            imageVector = DatePickIcons.Logo,
                            contentDescription = null,
                            modifier = Modifier.width(100.dp).align(Alignment.Center),
                            tint = MaterialTheme.colors.onBackground.copy(0.2f)
                                .compositeOver(MaterialTheme.colors.background)
                        )

                    }
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(place.name, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier.alpha(0.8f),
                text = place.address,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.alpha(0.6f),
                text = place.categories.joinToString(", ") { it.name },
                style = MaterialTheme.typography.body2
            )

        }

    }
}

fun LazyListScope.itemHorizontalPlacesWithHeader(
    title: String,
    places: List<Place>,
    onPlaceClicked: (Place) -> Unit,
    onMoreClicked: () -> Unit,
) {
    item {
        Column(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
            Spacer(modifier = Modifier.height(10.dp))
            Header(title, "더보기", onMoreButtonClicked = onMoreClicked)
            PlaceHorizontalList(places, onPlaceClicked)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}