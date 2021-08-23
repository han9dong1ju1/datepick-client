package app.hdj.datepick.android.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import timber.log.Timber

object NaverBlogUrlPreviewDownloader {

    fun fetchImageUrl(url: String): Flow<String?> = flow {

        val iframeUrl = Jsoup.connect(url)
            .timeout(30_000)
            .get()
            .body()
            .select("iframe")
            .attr("src")

        val document =
            Jsoup.connect("https://blog.naver.com/$iframeUrl").timeout(30_000).get().head()

        val imageUrl = document.select("meta[property=og:image]").attr("content")

        emit(imageUrl)

    }.flowOn(Dispatchers.IO).catch { Timber.e(it) }

}