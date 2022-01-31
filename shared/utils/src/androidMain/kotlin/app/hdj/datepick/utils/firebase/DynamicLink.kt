package app.hdj.datepick.utils.firebase

import android.net.Uri
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.net.UnknownServiceException

actual class DynamicLink actual constructor() {

    actual class AndroidParameters(
        val androidParameters: com.google.firebase.dynamiclinks.DynamicLink.AndroidParameters
    ) {
        actual class Builder {

            private val builder = com.google.firebase.dynamiclinks.DynamicLink.AndroidParameters.Builder()

            actual var minimumVersion: Int
                get() = builder.minimumVersion
                set(value) {
                    builder.minimumVersion = value
                }

            actual var packageName: String
                get() = ""
                set(value) {
                    // Not Used
                }

            actual fun build(): AndroidParameters {
                return AndroidParameters(builder.build())
            }
        }
    }

    actual class IosParameters(val iosParameters: com.google.firebase.dynamiclinks.DynamicLink.IosParameters) {
        actual class Builder actual constructor(bundleId: String) {

            private val builder = com.google.firebase.dynamiclinks.DynamicLink.IosParameters.Builder(bundleId)


            actual var appStoreId: String
                get() = builder.appStoreId
                set(value) {
                    builder.appStoreId = value
                }

            actual var minimumVersion: String
                get() = builder.minimumVersion
                set(value) {
                    builder.minimumVersion = value
                }

            actual fun build(): IosParameters {
                return IosParameters(builder.build())
            }
        }
    }

    actual class SocialMetaTagParameters(val socialMetaTagParameters: com.google.firebase.dynamiclinks.DynamicLink.SocialMetaTagParameters) {
        actual class Builder {

            private val builder = com.google.firebase.dynamiclinks.DynamicLink.SocialMetaTagParameters.Builder()


            actual var title: String
                get() = builder.title
                set(value) {
                    builder.title = value
                }


            actual var description: String
                get() = builder.description
                set(value) {
                    builder.description = value
                }

            actual var imageUrl: String
                get() = builder.imageUrl.toString()
                set(value) {
                    builder.imageUrl = Uri.parse(value)
                }

            actual fun build(): SocialMetaTagParameters {
                return SocialMetaTagParameters(builder.build())
            }
        }
    }

    actual class GoogleAnalyticsParameters(val googleAnalyticsParameters: com.google.firebase.dynamiclinks.DynamicLink.GoogleAnalyticsParameters) {
        actual class Builder {
            private val builder = com.google.firebase.dynamiclinks.DynamicLink.GoogleAnalyticsParameters.Builder()

            actual var source: String
                get() = builder.source
                set(value) {
                    builder.source = value
                }

            actual var medium: String
                get() = builder.medium
                set(value) {
                    builder.medium = value
                }


            actual var campaign: String
                get() = builder.campaign
                set(value) {
                    builder.campaign = value
                }


            actual fun build(): GoogleAnalyticsParameters {
                return GoogleAnalyticsParameters(builder.build())
            }
        }
    }

    actual class ItunesConnectAnalyticsParameters(val itunesConnectAnalyticsParameters: com.google.firebase.dynamiclinks.DynamicLink.ItunesConnectAnalyticsParameters) {
        actual class Builder {
            private val builder = com.google.firebase.dynamiclinks.DynamicLink.ItunesConnectAnalyticsParameters.Builder()

            actual var providerToken: String
                get() = builder.providerToken
                set(value) {
                    builder.providerToken = value
                }

            actual var campaignToken: String
                get() = builder.campaignToken
                set(value) {
                    builder.campaignToken = value
                }

            actual fun build(): ItunesConnectAnalyticsParameters = ItunesConnectAnalyticsParameters(builder.build())
        }
    }

    private val dynamicLink = Firebase.dynamicLinks

    actual class Builder(private val androidBuilder: DynamicLink.Builder) {

        actual var domainUriPrefix: String
            get() = androidBuilder.domainUriPrefix
            set(value) {
                androidBuilder.domainUriPrefix = value
            }

        actual var link: String
            get() = androidBuilder.link.toString()
            set(value) {
                androidBuilder.link = Uri.parse(value)
            }

        actual var androidParameters: AndroidParameters
            get() = throw UnsupportedOperationException()
            set(value) {
                androidBuilder.setAndroidParameters(value.androidParameters)
            }


        actual var iosParameters: IosParameters
            get() = throw UnsupportedOperationException()
            set(value) {
                androidBuilder.setIosParameters(value.iosParameters)
            }

        actual var socialMetaTagParameters: SocialMetaTagParameters
            get() = throw UnsupportedOperationException()
            set(value) {
                androidBuilder.setSocialMetaTagParameters(value.socialMetaTagParameters)
            }

        actual var googleAnalyticsParameters: GoogleAnalyticsParameters
            get() = throw UnsupportedOperationException()
            set(value) {
                androidBuilder.setGoogleAnalyticsParameters(value.googleAnalyticsParameters)
            }

        actual var itunesConnectAnalyticsParameters: ItunesConnectAnalyticsParameters
            get() = throw UnsupportedOperationException()
            set(value) {
                androidBuilder.setItunesConnectAnalyticsParameters(value.itunesConnectAnalyticsParameters)
            }
        

    }

    actual suspend fun shortLink(builder: Builder.() -> Unit): String? {
        return dynamicLink.shortLinkAsync {
            builder(Builder(this))
        }.await().shortLink?.toString()
    }

}