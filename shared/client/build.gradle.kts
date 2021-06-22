plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.0"
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

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
        frameworkName = "app/hdj/shared/client/client"
        podfile = project.file("../../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(KotlinX.coroutines.core)
                api(KotlinX.serialization.json)
                api(Ktor.client.core)
                api(Ktor.client.json)
                api(Ktor.client.serialization)
                api(Ktor.client.logging)
                api(Utils.multiplatformSettings)
                api(Utils.multiplatformSettingsSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {

                api(AndroidX.compose.compiler)
                api(AndroidX.compose.material)
                api(AndroidX.compose.foundation)
                api(AndroidX.compose.runtime)
                api(AndroidX.compose.ui)
                api(AndroidX.compose.ui.tooling)
                api(AndroidX.appCompat)

                api(AndroidX.paging.commonKtx)
                api(AndroidX.paging.compose)
                kapt(AndroidX.paging.runtimeKtx)

                api(AndroidX.navigation.compose)
                api(AndroidX.navigation.commonKtx)
                api(AndroidX.navigation.uiKtx)
                kapt(AndroidX.navigation.runtimeKtx)

                api(AndroidX.activity)

                api(AndroidX.security.cryptoKtx)

                api(AndroidX.lifecycle.viewModelCompose)
                api(AndroidX.lifecycle.viewModelKtx)
                api(AndroidX.lifecycle.liveDataKtx)

                kapt(AndroidX.hilt.compiler)
                api(AndroidX.hilt.navigationCompose)

                kapt(Google.dagger.hilt.compiler)
                api(Google.dagger.hilt.android)
                api(Google.accompanist.coil)
                api(Google.accompanist.insets)
                api(Google.accompanist.insets.ui)
                api(Google.accompanist.pager)
                api(Google.accompanist.pager.indicators)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Koin.core)
            }
        }
        val iosTest by getting
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