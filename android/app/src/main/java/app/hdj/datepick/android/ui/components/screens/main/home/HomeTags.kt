package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.utils.HorizontalMargin
import app.hdj.datepick.ui.utils.horizontalMargin
import app.hdj.shared.client.domain.StateData

private val TAG_HEIGHT = 40.dp
private val LEFT_SPACE = 20.dp + TAG_HEIGHT

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTags(tagState: StateData<List<String>>) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {

        LazyRow(
            modifier = Modifier
                .padding(start = LEFT_SPACE)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            when (tagState) {
                is StateData.Failed -> {

                }
                is StateData.Loading -> {

                }
                is StateData.Success -> {
                    itemsIndexed(tagState.data) { i, tag ->
                        if (i == 0) HorizontalMargin(10.dp)
                        Row(modifier = Modifier.fillParentMaxHeight()) {
                            Card(
                                modifier = Modifier
                                    .height(TAG_HEIGHT)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Text(
                                        text = tag,
                                        style = MaterialTheme.typography.body2,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                            HorizontalMargin(10.dp)
                        }
                    }
                }
            }

            horizontalMargin(10.dp)
        }

        Row(
            modifier = Modifier.fillMaxHeight()
        ) {

            HorizontalMargin()

            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .background(
                        MaterialTheme.colors.onSurface
                            .copy(alpha = 0.1f)
                            .compositeOver(MaterialTheme.colors.background)
                    )
                    .size(TAG_HEIGHT),
                onClick = {

                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Rounded.FilterList,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .width(10.dp)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colors.background,
                                MaterialTheme.colors.background.copy(0.0f),
                            )
                        )
                    )
            )

        }

    }
}