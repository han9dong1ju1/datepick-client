package app.hdj.datepick.android.ui.glance.placeRecommendation

import android.content.Context
import app.hdj.datepick.android.ui.glance.CoroutineGlanceAppWidgetReceiver
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class PlaceRecommendationAppWidgetReceiver :
    CoroutineGlanceAppWidgetReceiver<List<String>, PlaceRecommendationAppWidget>() {

    @Inject
    @ApplicationContext
    override lateinit var context : Context

    override suspend fun loadContent(): List<String> {
        delay(2000)
        return listOf("Hello", "World", "Glance")
    }

    override fun glanceAppWidget() = PlaceRecommendationAppWidget()


}