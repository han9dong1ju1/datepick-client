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
        frameworkName = "places"
        podfile = project.file("../../iosApp/Podfile")

        pod("GooglePlaces")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(KotlinX.coroutines.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(KotlinX.coroutines.playServices)
                implementation("com.google.android.libraries.places:places:_")
                implementation("com.google.maps.android:places-v3-ktx:_")
            }
        }
        val iosMain by getting
    }
}

android {
    compileSdkPreview = Properties.androidCompileSDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdkPreview = Properties.androidTargetSDK
    }
}
