package app.hdj.datepick.android.ui.glance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.glance.GlanceId
import androidx.glance.LocalGlanceId
import androidx.glance.appwidget.GlanceAppWidget

abstract class DataGlanceAppWidget<Data>(
    initialData : Data? = null
) : GlanceAppWidget() {

    var glanceId by mutableStateOf<GlanceId?>(null)
    var data : Data? by mutableStateOf(initialData)

    @Composable
    abstract fun Content(data : Data?)

    @Composable
    override fun Content() {
        glanceId = LocalGlanceId.current
        Content(data)
    }
}