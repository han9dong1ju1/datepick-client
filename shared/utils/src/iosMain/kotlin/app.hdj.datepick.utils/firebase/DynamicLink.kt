package app.hdj.datepick.utils.firebase


actual class DynamicLink actual constructor() {

    actual class AndroidParameters(
    ) {
        actual class Builder {
            actual var minimumVersion: Int
                get() = TODO("Not yet implemented")
                set(value) {}
            actual var packageName: String
                get() = TODO("Not yet implemented")
                set(value) {}

            actual fun build(): AndroidParameters {
                TODO("Not yet implemented")
            }

        }
    }

    actual class IosParameters(
    ) {
        actual class Builder actual constructor(bundleId: String) {
            actual var appStoreId: String
                get() = TODO("Not yet implemented")
                set(value) {}
            actual var minimumVersion: String
                get() = TODO("Not yet implemented")
                set(value) {}

            actual fun build(): IosParameters {
                TODO("Not yet implemented")
            }

        }
    }

    actual class SocialMetaTagParameters(
    ) {
        actual class Builder {
            actual var title: String
                get() = TODO("Not yet implemented")
                set(value) {}
            actual var description: String
                get() = TODO("Not yet implemented")
                set(value) {}
            actual var imageUrl: String
                get() = TODO("Not yet implemented")
                set(value) {}

            actual fun build(): SocialMetaTagParameters {
                TODO("Not yet implemented")
            }

        }
    }

    actual class GoogleAnalyticsParameters(
    ) {
        actual class Builder {
            actual var campaign: String
                get() = TODO("Not yet implemented")
                set(value) {}
            actual var medium: String
                get() = TODO("Not yet implemented")
                set(value) {}
            actual var source: String
                get() = TODO("Not yet implemented")
                set(value) {}

            actual fun build(): GoogleAnalyticsParameters {
                TODO("Not yet implemented")
            }
        }
    }

    actual class ItunesConnectAnalyticsParameters(
    ) {
        actual class Builder {
            actual var providerToken: String
                get() = TODO("Not yet implemented")
                set(value) {}
            actual var campaignToken: String
                get() = TODO("Not yet implemented")
                set(value) {}

            actual fun build(): ItunesConnectAnalyticsParameters {
                TODO("Not yet implemented")
            }
        }
    }

    actual class Builder() {
        actual var link: String
            get() = TODO("Not yet implemented")
            set(value) {}
        actual var domainUriPrefix: String
            get() = TODO("Not yet implemented")
            set(value) {}
        actual var androidParameters: AndroidParameters
            get() = TODO("Not yet implemented")
            set(value) {}
        actual var iosParameters: IosParameters
            get() = TODO("Not yet implemented")
            set(value) {}
        actual var socialMetaTagParameters: SocialMetaTagParameters
            get() = TODO("Not yet implemented")
            set(value) {}
        actual var googleAnalyticsParameters: GoogleAnalyticsParameters
            get() = TODO("Not yet implemented")
            set(value) {}
        actual var itunesConnectAnalyticsParameters: ItunesConnectAnalyticsParameters
            get() = TODO("Not yet implemented")
            set(value) {}
    }

    actual suspend fun shortLink(builder: Builder.() -> Unit): String? {
        return TODO("")
    }

}