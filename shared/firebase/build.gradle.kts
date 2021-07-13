plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.0"
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
}

version = "1.0"

kotlin {

    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }

    android()
    iosX64("ios")

    cocoapods {
        summary = "DatePick Multiplatform"
        homepage = "https://github.com/han9dong1ju1/DatePick"
        ios.deploymentTarget = "14.0"
        frameworkName = "firebase"
        podfile = project.file("../../iosApp/Podfile")

        pod("FirebaseAnalytics")
        pod("FirebaseStorage")
        pod("FirebaseMessaging")
        pod("FirebaseRemoteConfig")
        pod("FirebaseCrashlytics")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(KotlinX.coroutines.core)
                implementation(KotlinX.serialization.core)
                api(Firebase.multiplatform.auth)
                api(Firebase.multiplatform.firestore)
            }
        }
        val androidMain by getting {
            dependencies {
                api(project.dependencies.platform(Firebase.bom))
                api(Firebase.cloudStorageKtx)
                api(Firebase.cloudMessaging)
                api(Firebase.remoteConfigKtx)
                api(Firebase.crashlyticsKtx)
                api(Firebase.analyticsKtx)

                api(KotlinX.coroutines.android)
                api(KotlinX.coroutines.playServices)
            }
        }
        val iosMain by getting
    }
}

android {
    compileSdk = Properties.androidCompileSDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdk = Properties.androidTargetSDK
    }
}
