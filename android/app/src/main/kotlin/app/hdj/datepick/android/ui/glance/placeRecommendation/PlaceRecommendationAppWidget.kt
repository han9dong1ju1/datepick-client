package app.hdj.datepick.android.ui.glance.placeRecommendation

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.*
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.unit.ColorProvider
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import app.hdj.datepick.android.ui.MainActivity
import app.hdj.datepick.android.ui.glance.DataGlanceAppWidget

class PlaceRecommendationAppWidget : DataGlanceAppWidget<List<String>>() {

    override val sizeMode: SizeMode = SizeMode.Single

    @Composable
    override fun Content(data: List<String>?) {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(day = Color.White, night = Color(0xFF141414))
                .appWidgetBackground()
                .cornerRadius(20.dp)
                .padding(20.dp)
                .clickable(actionStartActivity<MainActivity>())
        ) {
            Text(
                text = "장소 추천",
                style = TextStyle(
                    color = ColorProvider(day = Color.Black, night = Color.White),
                    fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = GlanceModifier.height(10.dp))
            Text(
                text = "장소 추천에 관한 내용을 여기에 사용할 것입니다.",
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
            )

            LazyColumn {
                items(data ?: emptyList()) {
                    Text(it)
                }
            }

        }
    }

}

@Composable
private fun PlaceRecommendationAppWidgetListItem() {
}