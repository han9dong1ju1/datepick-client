package app.hdj.datepick.utils

import app.hdj.datepick.utils.firebase.androidParameters
import app.hdj.datepick.utils.firebase.dynamicLinks
import app.hdj.datepick.utils.firebase.iosParameters
import app.hdj.datepick.utils.firebase.socialMetaTagParameters
import dev.gitlive.firebase.Firebase


const val DEEPLINK_URL = "datepick://"
const val EXTERNAL_DEEPLINK_URL = "https://deeplink.datepick.app"
//const val FIREBASE_DYNAMIC_LINK_DOMAIN_URL = "https://datepick.app/deeplink"
const val FIREBASE_DYNAMIC_LINK_DOMAIN_URL = "https://datepick.page.link"

const val IosBundleId = "app.hdj.datepick.ios"

suspend fun createDynamicLink(
    deeplinkSuffix: String,
    linkTitle: String,
    linkContent: String,
    linkPhotoUrl: String,
    androidMinimumVersion : Int,
    iOSMinimumVersion : String,
): String? {
    return Firebase.dynamicLinks.shortLink {
        link = "$EXTERNAL_DEEPLINK_URL/$deeplinkSuffix"
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
            imageUrl = linkPhotoUrl
        }
    }
}