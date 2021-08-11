package app.hdj.datepick.android.utils

import android.net.Uri
import androidx.navigation.navDeepLink
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber

const val DEEPLINK_URL = "datepick://"
const val EXTERNAL_DEEPLINK_URL = "https://deeplink.datepick.app"

private const val IosBundleId = "app.hdj.datepick.ios"

fun datePickNavDeepLink(suffix: String) = navDeepLink {
    uriPattern = "$DEEPLINK_URL$suffix"
}

fun externalDatePickNavDeepLink(suffix: String) = navDeepLink {
    uriPattern = "$EXTERNAL_DEEPLINK_URL/$suffix"
}

suspend fun createDynamicLink(
    deeplinkSuffix: String,
    linkTitle: String,
    linkContent: String,
    linkPhotoUrl: String
): Uri? {
    return Firebase.dynamicLinks.shortLinkAsync {
        link = Uri.parse("$EXTERNAL_DEEPLINK_URL/$deeplinkSuffix")
        domainUriPrefix = "https://datepick.page.link"
        androidParameters {
            minimumVersion = 2021_1_00_00
        }
        iosParameters(IosBundleId) {
            appStoreId = ""
            minimumVersion = "2021.1.00.00"
        }
        socialMetaTagParameters {
            title = linkTitle
            description = linkContent
            imageUrl = Uri.parse(linkPhotoUrl)
        }
    }.runCatching {
        await().shortLink
    }.onFailure {
        Timber.e(it)
    }.getOrNull()
}