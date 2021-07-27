package app.hdj.datepick.android.ui.components.list.notice_event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import app.hdj.shared.client.domain.entity.NoticeEvent
import coil.size.Scale
import coil.transform.RoundedCornersTransformation

private val NOTICE_EVENT_SMALL_ITEM_WIDTH = 340.dp
private val NOTICE_EVENT_SMALL_ITEM_HEIGHT = 200.dp

private val NOTICE_EVENT_LARGE_ITEM_HEIGHT = 400.dp

@ExperimentalMaterialApi
@Composable
fun NoticeEventSmallItem(
    noticeEvent: NoticeEvent,
    onClick: (NoticeEvent) -> Unit
) {

    Card(
        onClick = { onClick(noticeEvent) },
        modifier = Modifier.size(NOTICE_EVENT_SMALL_ITEM_WIDTH, NOTICE_EVENT_SMALL_ITEM_HEIGHT)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            NetworkImage(
                imagePainter = rememberUrlImagePainter(
                    noticeEvent.photoUrl,
                    builder = {
                        scale(Scale.FILL)
                        transformations(RoundedCornersTransformation(15.dp.value))
                    }),
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.BottomStart)
            ) {

                Text(
                    text = noticeEvent.title,
                    maxLines = 2,
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = noticeEvent.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = Color.White.copy(0.5f),
                    style = MaterialTheme.typography.body2
                )

            }

        }
    }

}

