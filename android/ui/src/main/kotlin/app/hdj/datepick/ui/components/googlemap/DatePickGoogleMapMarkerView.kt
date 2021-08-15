package app.hdj.datepick.ui.components.googlemap

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.maps.android.ktx.model.markerOptions
import kotlinx.coroutines.delay

@SuppressLint("ViewConstructor")
class DatePickGoogleMapMarkerView(
    context: Context,
    private val title: String,
    private val onBitmapGenerated: (Bitmap) -> Unit
) : FrameLayout(context) {

    init {
        val view = ComposeView(context)
        view.setContent { DatePickGoogleMapMarker(title) }
        addView(view)

        view.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val bitmap = view.createBitmapFromView()
                onBitmapGenerated(bitmap)
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

    }


    private fun View.createBitmapFromView(): Bitmap {
        layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )

        measure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )

        layout(0, 0, width, height)

        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        canvas.setBitmap(bitmap)
        draw(canvas)

        return bitmap
    }

}

@Composable
fun DatePickGoogleMapMarker(title: String) {

    Surface {
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            style = MaterialTheme.typography.body2
        )
    }

}

@Composable
fun DatePickGoogleMapMarkerViewWrapping(title: String, onBitmapGenerated: (Bitmap) -> Unit) {
    AndroidView(factory = {
        DatePickGoogleMapMarkerView(it, title, onBitmapGenerated)
    })
}