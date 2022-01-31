package app.hdj.datepick.utils.firebase

import dev.gitlive.firebase.Firebase

expect class DynamicLink constructor() {

    class AndroidParameters {
        class Builder() {
            var minimumVersion: Int
            var packageName: String
            fun build(): AndroidParameters
        }
    }

    class IosParameters {
        class Builder(bundleId: String) {
            var appStoreId: String
            var minimumVersion: String
            fun build(): IosParameters
        }
    }

    class SocialMetaTagParameters {
        class Builder() {
            var title: String
            var description: String
            var imageUrl: String
            fun build(): SocialMetaTagParameters
        }
    }

    class GoogleAnalyticsParameters {
        class Builder() {
            var campaign: String
            var medium: String
            var source: String
            fun build(): GoogleAnalyticsParameters
        }
    }

    class ItunesConnectAnalyticsParameters {
        class Builder() {
            var providerToken: String
            var campaignToken: String
            fun build(): ItunesConnectAnalyticsParameters
        }
    }

    class Builder {
        var link: String
        var domainUriPrefix: String
        var androidParameters: AndroidParameters
        var iosParameters: IosParameters
        var socialMetaTagParameters: SocialMetaTagParameters
        var googleAnalyticsParameters: GoogleAnalyticsParameters
        var itunesConnectAnalyticsParameters: ItunesConnectAnalyticsParameters
    }

    suspend fun shortLink(builder: Builder.() -> Unit): String?

}

val Firebase.dynamicLinks: DynamicLink get() = DynamicLink()

fun DynamicLink.Builder.androidParameters(init: DynamicLink.AndroidParameters.Builder.() -> Unit) {
    val builder = DynamicLink.AndroidParameters.Builder()
    builder.init()
    androidParameters = builder.build()
}

fun DynamicLink.Builder.iosParameters(bundleId: String, init: DynamicLink.IosParameters.Builder.() -> Unit) {
    val builder = DynamicLink.IosParameters.Builder(bundleId)
    builder.init()
    iosParameters = builder.build()
}

fun DynamicLink.Builder.socialMetaTagParameters(init: DynamicLink.SocialMetaTagParameters.Builder.() -> Unit) {
    val builder = DynamicLink.SocialMetaTagParameters.Builder()
    builder.init()
    socialMetaTagParameters = builder.build()
}

fun DynamicLink.Builder.googleAnalyticsParameters(init: DynamicLink.GoogleAnalyticsParameters.Builder.() -> Unit) {
    val builder = DynamicLink.GoogleAnalyticsParameters.Builder()
    builder.init()
    googleAnalyticsParameters = builder.build()
}

fun DynamicLink.Builder.itunesConnectAnalyticsParameters(init: DynamicLink.ItunesConnectAnalyticsParameters.Builder.() -> Unit) {
    val builder = DynamicLink.ItunesConnectAnalyticsParameters.Builder()
    builder.init()
    itunesConnectAnalyticsParameters = builder.build()
}
