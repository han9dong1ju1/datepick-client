package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.ListHeader


@Composable
fun PlaceDetailScreenReviewSection(
    onClick : () -> Unit
) {


    ListHeader(title = "리뷰")

    LazyRow(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .height(300.dp)
    ) {

    }

}