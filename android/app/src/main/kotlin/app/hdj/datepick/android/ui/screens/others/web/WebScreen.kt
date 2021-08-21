package app.hdj.datepick.android.ui.screens.others.web

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
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

    var webTitle by remember { mutableStateOf("") }
    var webUrl by remember { mutableStateOf(url) }
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
                            text = webTitle,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = webUrl, style = MaterialTheme.typography.caption,
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

            AndroidView(factory = ::WebView) { webView ->
                webView.webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        progress = newProgress
                    }
                }
                webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        webTitle = view?.title.orEmpty()
                        webUrl = view?.url.orEmpty()
                    }

                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        val requestedUrl = request?.url.toString()
                        if (!requestedUrl.startsWith("http")) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(requestedUrl)).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            webView.context.startActivity(intent)
                            return true
                        }
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
                webView.settings.javaScriptEnabled = true
                webView.settings.domStorageEnabled = true
                webView.loadUrl(url)
            }
        }

    }

}