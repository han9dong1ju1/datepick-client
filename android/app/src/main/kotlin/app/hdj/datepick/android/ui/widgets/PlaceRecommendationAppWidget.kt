package app.hdj.datepick.android.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionLaunchActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import app.hdj.datepick.android.ui.MainActivity

class PlaceRecommendationAppWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Column(
            modifier = GlanceModifier.fillMaxSize()
                .background(day = Color.White, night = Color.Black)
                .padding(20.dp)
                .clickable(actionLaunchActivity<MainActivity>())
        ) {
            Text(text = "장소 추천", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = GlanceModifier.height(10.dp))
            Text(text = "장소 추천에 관한 내용을 여기에 사용할 것입니다.", style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium))
        }
    }

}