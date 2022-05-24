package app.hdj.datepick.android.ui.components.googlemap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import app.hdj.datepick.android.R
import app.hdj.datepick.domain.model.place.Place


class PlaceMarkerIconGenerator(context: Context) {

    private val markerView = LayoutInflater.from(context)
        .inflate(R.layout.v_place_google_map_marker, null) as ViewGroup

    fun makeIcon(place: Place): Bitmap {

        markerView.findViewById<TextView>(R.id.tv_title).text = place.name

        val measureSpec: Int = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        markerView.measure(measureSpec, measureSpec)

        val measuredWidth = markerView.measuredWidth
        val measuredHeight = markerView.measuredHeight

        markerView.layout(0, 0, measuredWidth, measuredHeight)

        val r: Bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
        r.eraseColor(Color.TRANSPARENT)
        val canvas = Canvas(r)

        markerView.draw(canvas)
        return r
    }

}
