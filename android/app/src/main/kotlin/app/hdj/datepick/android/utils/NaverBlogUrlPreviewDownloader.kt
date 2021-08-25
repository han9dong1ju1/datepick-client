package app.hdj.datepick.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import app.hdj.datepick.domain.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import timber.log.Timber

@Composable
fun loadNaverBlogUrlPreviewImage(
    url : String
): State<LoadState<String>> = produceState<LoadState<String>>(initialValue = LoadState.Loading(), url) {

    kotlin.runCatching {

        val iframeUrl = Jsoup.connect(url)
            .timeout(30_000)
            .get()
            .body()
            .select("iframe")
            .attr("src")

        val document =
            Jsoup.connect("https://blog.naver.com/$iframeUrl").timeout(30_000).get().head()

        val imageUrl = document.select("meta[property=og:image]").attr("content")
        value = LoadState.success(imageUrl)
    }.onFailure {
        value = LoadState.failed(it)
    }

}

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