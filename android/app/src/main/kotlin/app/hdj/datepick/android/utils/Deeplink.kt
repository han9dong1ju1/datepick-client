package app.hdj.datepick.android.utils

import androidx.navigation.navDeepLink
import app.hdj.datepick.utils.DEEPLINK_URL
import app.hdj.datepick.utils.EXTERNAL_DEEPLINK_URL

fun datepickDeepLink(suffix: String) = "$DEEPLINK_URL$suffix"

fun datepickExternalDeepLink(suffix: String) = "$EXTERNAL_DEEPLINK_URL/$suffix"