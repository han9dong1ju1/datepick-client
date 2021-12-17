package app.hdj.datepick.android.ui.glance.diary

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.background
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import app.hdj.datepick.android.ui.MainActivity
import app.hdj.datepick.android.ui.glance.DataGlanceAppWidget
import app.hdj.datepick.domain.model.diary.Diary

class DiaryAppWidget : DataGlanceAppWidget<List<Diary>>() {

    override val sizeMode: SizeMode = SizeMode.Single

    @Composable
    override fun Content(data: List<Diary>?) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(day = Color.White, night = Color(0xFF141414))
                .appWidgetBackground()
                .cornerRadius(20.dp)
                .padding(20.dp)
                .clickable(actionStartActivity<MainActivity>())
        ) {
            if (data != null) {
                LazyColumn {
                    items(data) {
                        DiaryWidgetListItem(it)
                    }
                }
            }
        }
    }

}

@Composable
private fun DiaryWidgetListItem(diary: Diary) {

    Row(modifier = GlanceModifier.fillMaxWidth().padding(10.dp)) {

        Spacer(modifier = GlanceModifier.width(10.dp))

        Column {

            Text(
                text = "안국동 데이트",
                style = TextStyle(fontSize = 16.sp)
            )

            Spacer(modifier = GlanceModifier.height(6.dp))

            Text(
                text = "2021. 11. 1",
                style = TextStyle(fontSize = 12.sp)
            )

        }

    }
}