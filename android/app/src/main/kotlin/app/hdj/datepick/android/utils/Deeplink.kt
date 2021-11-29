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
const val FIREBASE_DYNAMIC_LINK_DOMAIN_URL = "https://datepick.app/deeplink"

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
    linkPhotoUrl: String,
    androidMinimumVersion : Int,
    iOSMinimumVersion : String,
): Uri? {
    return Firebase.dynamicLinks.shortLinkAsync {
        link = Uri.parse("$EXTERNAL_DEEPLINK_URL/$deeplinkSuffix")
        domainUriPrefix = FIREBASE_DYNAMIC_LINK_DOMAIN_URL
        androidParameters {
            minimumVersion = androidMinimumVersion
        }
        iosParameters(IosBundleId) {
            appStoreId = ""
            minimumVersion = iOSMinimumVersion
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