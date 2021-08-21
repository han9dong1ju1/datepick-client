package app.hdj.datepick.android.ui.screens.web

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreen(url: String) {

    var webTitle by remember { mutableStateOf<String?>("로딩중...") }
    var progress by remember { mutableStateOf(0) }

    DatePickScaffold(
        topBar = {
            DatePickTopAppBar(
                navigationIcon = { TopAppBarBackButton(Icons.Rounded.Close) },
                actions = {

                },
                title = {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = webTitle ?: "에러가 발생했습니다.",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = url, style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface.copy(0.3f)
                                .compositeOver(MaterialTheme.colors.surface)
                        )
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {

            AnimatedVisibility(visible = progress != 100) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp),
                    progress = progress.div(100f)
                )
            }

            AndroidView(factory = ::WebView) { view ->
                view.webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        progress = newProgress
                    }
                }
                view.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        webTitle = view?.title
                    }
                }
                view.settings.javaScriptEnabled = true
                view.settings.domStorageEnabled = true
                view.loadUrl(url)
            }
        }

    }

}