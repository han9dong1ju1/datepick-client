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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.district.District
import app.hdj.datepick.ui.styles.tertiary
import app.hdj.datepick.utils.location.LatLng

@Composable
fun DistrictListHorizontalItem(
    district: District,
    onDistrictClicked: (District) -> Unit
) {

    val surfaceColor = listOf(
        Color(0xFFFF5D5D),
        Color(0xFFFF417C),
        Color(0xFF26BF79),
        Color(0xFFFFC834),
        Color(0xFFFF836C),
        Color(0xFF34B9FF),
        Color(0xFFFFC834),
        Color(0xFF886CFF),
        Color(0xFF28E545),
    ).random()

    Surface(
        modifier = Modifier.width(150.dp),
        shape = RoundedCornerShape(20.dp),
        color = surfaceColor,
        contentColor = if (surfaceColor.luminance() > 0.5f) Color.Black.copy(surfaceColor.luminance()) else Color.White,
        onClick = { onDistrictClicked(district) },
    ) {
        Text(
            text = district.name,
            modifier = Modifier.padding(20.dp)
        )
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
                DistrictListHorizontalItem(district, onDistrictClicked)
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}