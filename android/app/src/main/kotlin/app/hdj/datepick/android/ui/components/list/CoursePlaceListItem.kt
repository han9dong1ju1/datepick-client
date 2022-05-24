package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.place.Place

enum class CoursePlaceListItemType {
    TOP,
    MIDDLE,
    BOTTOM
}

@Composable
fun CoursePlaceListItem(
    modifier: Modifier = Modifier,
    dragHandle : @Composable () -> Unit,
    place: Place,
    coursePlaceListItemType: CoursePlaceListItemType,
    inEditMode: Boolean,
    onPlaceClicked: (Place) -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth().clickable { onPlaceClicked(place) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (inEditMode) {
            Spacer(modifier = Modifier.width(40.dp))
            dragHandle()
        }
        Spacer(modifier = Modifier.width(40.dp))
        CoursePlaceListItemLine(coursePlaceListItemType)
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = place.name,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = place.categories.joinToString { it.name },
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Composable
private fun CoursePlaceListItemLine(
    coursePlaceListItemType: CoursePlaceListItemType
) {

    Box(modifier = Modifier.height(80.dp)) {

        Box(
            modifier = Modifier.width(10.dp).run {
                when (coursePlaceListItemType) {
                    CoursePlaceListItemType.TOP -> height(46.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            MaterialTheme.colors.secondary,
                            RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp)
                        )
                    CoursePlaceListItemType.MIDDLE -> height(80.dp)
                        .background(
                            MaterialTheme.colors.secondary
                        )
                        .align(Alignment.Center)
                    CoursePlaceListItemType.BOTTOM -> height(46.dp)
                        .align(Alignment.TopCenter)
                        .background(
                            MaterialTheme.colors.secondary,
                            RoundedCornerShape(bottomEnd = 5.dp, bottomStart = 5.dp)
                        )
                }
            }
        )

        Box(
            modifier = Modifier.size(6.dp)
                .background(MaterialTheme.colors.onBackground, RoundedCornerShape(3.dp))
                .align(Alignment.Center)
        )

    }

}