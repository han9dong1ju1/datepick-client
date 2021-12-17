package app.hdj.datepick.android.ui.glance.diary

import android.content.Context
import androidx.paging.PagingData
import app.hdj.datepick.android.ui.glance.CoroutineGlanceAppWidgetReceiver
import app.hdj.datepick.domain.model.diary.Diary
import coil.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class DiaryAppWidgetReceiver :
    CoroutineGlanceAppWidgetReceiver<List<Diary>, DiaryAppWidget>() {

    @Inject
    @ApplicationContext
    override lateinit var context: Context

    @Inject
    lateinit var imageLoader: ImageLoader

    override suspend fun loadContent(): List<Diary> {

        return (0..10).map {
            object : Diary {
                override val id: Long get() = System.currentTimeMillis()
            }
        }
    }

    override fun glanceAppWidget() = DiaryAppWidget()

}