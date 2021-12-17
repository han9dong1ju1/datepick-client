package app.hdj.datepick.android.ui.glance

import android.content.Context
import androidx.compose.runtime.snapshotFlow
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class CoroutineGlanceAppWidgetReceiver<Data, W : DataGlanceAppWidget<Data>> :
    GlanceAppWidgetReceiver(), CoroutineScope {

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main

    override fun onDisabled(context: Context?) {
        cancel()
        super.onDisabled(context)
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        cancel()
        super.onDeleted(context, appWidgetIds)
    }

    abstract val context: Context

    abstract suspend fun loadContent(): Data

    override val glanceAppWidget: GlanceAppWidget
        get() = glanceAppWidget().apply {
            launch {
                data = loadContent()

                val currentGlanceId = snapshotFlow { glanceId }.filterNotNull().firstOrNull()

                if (currentGlanceId != null) {
                    update(context, currentGlanceId)
                }
            }
        }

    abstract fun glanceAppWidget(): W

}