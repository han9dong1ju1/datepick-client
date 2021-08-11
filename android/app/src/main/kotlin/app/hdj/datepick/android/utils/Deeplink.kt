package app.hdj.datepick.android.utils

import androidx.navigation.navDeepLink

const val DEEPLINK_URL = "datepick://"

fun datePickNavDeepLink(suffix: String) = navDeepLink {
    uriPattern = "$DEEPLINK_URL$suffix"
}