package app.hdj.datepick.android.utils

import androidx.navigation.navDeepLink
import app.hdj.datepick.utils.DEEPLINK_URL
import app.hdj.datepick.utils.EXTERNAL_DEEPLINK_URL

fun datePickNavDeepLink(suffix: String) = navDeepLink {
    uriPattern = "$DEEPLINK_URL$suffix"
}

fun externalDatePickNavDeepLink(suffix: String) = navDeepLink {
    uriPattern = "$EXTERNAL_DEEPLINK_URL/$suffix"
}