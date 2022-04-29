package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.badge.TextBadge
import app.hdj.datepick.domain.model.district.District
import app.hdj.datepick.utils.location.LatLng

@Composable
fun DistrictListHorizontalItem(
    district: District,
    isHighlighted: Boolean,
    onDistrictClicked: (District) -> Unit
) {

    Surface(
        modifier = Modifier.width(160.dp).height(160.dp),
        shape = RoundedCornerShape(20.dp),
        color = if (isHighlighted) MaterialTheme.colors.secondary.copy(0.1f)
            .compositeOver(MaterialTheme.colors.background) else MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        onClick = { onDistrictClicked(district) },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(20.dp).align(Alignment.BottomStart)) {
                if (isHighlighted) {
                    TextBadge("인기", MaterialTheme.colors.secondary)
                    Spacer(modifier = Modifier.height(15.dp))
                }

                Text(
                    text = district.name,
                    style = MaterialTheme.typography.h6,
                )
            }
        }
    }

}

@Composable
@Preview
private fun DistrictListHorizontalItemPreview() {
    DistrictListHorizontalItem(
        district = District(
            id = 1,
            name = "안국, 삼청, 북촌",
            latLng = LatLng(0.0, 0.0)
        ),
        isHighlighted = false,
        onDistrictClicked = {}
    )
}

fun LazyListScope.itemHorizontalDistrictList(
    districts: List<District>,
    onDistrictClicked: (District) -> Unit
) {
    item {
        LazyRow(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
            itemsIndexed(districts) { index, district ->
                if (index == 0) Spacer(modifier = Modifier.width(20.dp))
                DistrictListHorizontalItem(
                    district,
                    index <= 2,
                    onDistrictClicked
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
        Spacer(Modifier.height(20.dp))
    }
}