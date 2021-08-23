package app.hdj.datepick.android.ui.screens.others.web

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import timber.log.Timber

@Composable
private fun rememberWebViewWithLifecycle(): WebView {
    val context = LocalContext.current
    val webView = remember {
        WebView(context)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle, webView) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> webView.onResume()
                Lifecycle.Event.ON_PAUSE -> webView.onPause()
                else -> {

                }
            }
        }

        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return webView
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreen(url: String) {

    val context = LocalContext.current

    val webView = rememberWebViewWithLifecycle()

    var webTitle by remember { mutableStateOf("") }
    var webUrl by remember { mutableStateOf(url) }
    var progress by remember { mutableStateOf(0) }

    var menuExpanded by remember { mutableStateOf(false) }

    val navController = LocalAppNavController.current

    val backPressedCallback = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val lifecycleOwner = LocalLifecycleOwner.current

    SideEffect {
        backPressedCallback?.addCallback(lifecycleOwner) {
            if (webView.canGoBack()) webView.goBack()
            else navController.popBackStack()
        }
    }

    DatePickScaffold(
        topBar = {
            DatePickTopAppBar(
                navigationIcon = { TopAppBarBackButton(Icons.Rounded.Close) },
                actions = {

                    Box {

                        IconButton({
                            menuExpanded = true
                        }, content = {
                            Icon(Icons.Rounded.MoreVert, null)
                        })

                        DropdownMenu(
                            modifier = Modifier.requiredWidth(200.dp),
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }) {
                            DropdownMenuItem(
                                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                                onClick = {
                                    context.startActivity(Intent.createChooser(Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, webUrl)
                                        type = "text/plain"
                                    }, "공유"))
                                }) {
                                Icon(
                                    imageVector = Icons.Rounded.Share,
                                    modifier = Modifier.size(16.dp),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "공유하기")
                            }
                            DropdownMenuItem(
                                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                                onClick = {
                                    context.startActivity(Intent(ACTION_VIEW, Uri.parse(webUrl)))
                                }) {
                                Icon(
                                    imageVector = Icons.Rounded.Web,
                                    modifier = Modifier.size(16.dp),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "웹 브라우저에서 열기")
                            }
                        }
                    }

                },
                title = {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = webTitle,
                            style = MaterialTheme.typography.subtitle1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = webUrl, style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface.copy(0.3f)
                                .compositeOver(MaterialTheme.colors.surface),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
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

            AndroidView(factory = { webView }) { webView ->

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
                            val intent = if (requestedUrl.startsWith("intent")) {
                                Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                            } else {
                                Intent(Intent.ACTION_VIEW, Uri.parse(requestedUrl)).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                            }
                            webView.context.runCatching {
                                startActivity(intent)
                            }.onFailure(Timber.Forest::e)
                            return true
                        }
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }

                webView.settings.apply {
                    javaScriptCanOpenWindowsAutomatically = true
                    javaScriptEnabled = true
                    cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE
                    domStorageEnabled = true
                    mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    builtInZoomControls = false
                    displayZoomControls = false
                }

                webView.loadUrl(url)
            }
        }

    }

}