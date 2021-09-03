package app.hdj.datepick.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import app.hdj.datepick.domain.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

@Composable
fun loadNaverBlogUrlPreviewImage(
    url: String
): State<LoadState<String>> =
    produceState<LoadState<String>>(initialValue = LoadState.Loading(), url) {
        value = kotlin.runCatching {
            withContext(Dispatchers.IO) {
                val iframeUrl = Jsoup.connect(url)
                    .timeout(30_000)
                    .get()
                    .body()
                    .select("iframe")
                    .attr("src")

                val document =
                    Jsoup.connect("https://blog.naver.com/$iframeUrl").timeout(30_000).get().head()
                document.select("meta[property=og:image]").attr("content")
            }
        }.fold({
            LoadState.success(it)
        }) {
            LoadState.failed(it)
        }

    }