package app.hdj.datepick.android.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CreateQrCode(
    url : String,
    size : IntOffset,
    onBitmapReceived: (Bitmap?) -> Unit
) {
    LaunchedEffect(true) {
        val bitmap = withContext(Dispatchers.IO) {
            QrCodeGenerator.encodeAsBitmap(
                url,
                size.x, size.y
            )
        }
        onBitmapReceived(bitmap)
    }
}

object QrCodeGenerator {
    fun encodeAsBitmap(str: String, width: Int, height: Int): Bitmap? {
        val result: BitMatrix
        try {
            result = MultiFormatWriter().encode(str,
                BarcodeFormat.QR_CODE, width, height, null)
        } catch (iae: IllegalArgumentException) {
            return null
        }
        val width = result.width
        val height = result.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result.get(x, y)) -0x1000000 else -0x1
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

}