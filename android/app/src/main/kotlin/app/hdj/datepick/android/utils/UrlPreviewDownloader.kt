package app.hdj.datepick.android.utils

import android.webkit.URLUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import timber.log.Timber
import java.net.URI
import java.net.URISyntaxException

object UrlPreviewDownloader {

    fun fetchImageUrl(url: String): Flow<String?> = flow {

        val document = Jsoup.connect(url)
            .timeout(30_000)
            .get()
            .head()

        val url = document.select("meta[property=og:image]").attr("content")

        Timber.d("IMAGE URL : $url")

        emit(url)

//        emit(document.select("meta[property=og:url]").attr("content"))

    }.flowOn(Dispatchers.IO).catch { Timber.e(it) }


    private fun resolveURL(url: String, part: String): String? {
        return if (URLUtil.isValidUrl(part)) {
            part
        } else {
            var baseUri: URI? = null
            try {
                baseUri = URI(url)
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            baseUri = baseUri?.resolve(part)
            baseUri?.toString()
        }
    }

}