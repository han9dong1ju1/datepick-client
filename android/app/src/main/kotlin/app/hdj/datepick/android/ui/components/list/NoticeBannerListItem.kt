package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.notice.Notice

@Composable
fun NoticeBannerListItem(
    notice: Notice,
    onClick: (Notice) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp),
        onClick = {
            onClick(notice)
        },
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth().padding(20.dp)) {

           Column {
               Text(text = notice.title)
               Spacer(modifier = Modifier.height(6.dp))
               Text(text = notice.content)
           }

        }

    }

}