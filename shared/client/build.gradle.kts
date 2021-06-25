plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.0"
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
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
                implementation(project(":shared:firebase"))
                api(KotlinX.coroutines.core)
                api(KotlinX.serialization.json)
                api(Ktor.client.core)
                api(Ktor.client.json)
                api(Ktor.client.serialization)
                api(Ktor.client.logging)
                api(Ktor.client.cio)
                api(Utils.multiplatformSettings)
                api(Utils.multiplatformCoroutines)
                api(Utils.multiplatformSettingsSerialization)
                api(Utils.kotlinxDateTime)
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

                api(AndroidX.navigation.compose)
                api(AndroidX.navigation.commonKtx)
                api(AndroidX.navigation.uiKtx)

                api(AndroidX.activity)

                api(AndroidX.security.cryptoKtx)

                api(AndroidX.lifecycle.viewModelCompose)
                api(AndroidX.lifecycle.viewModelKtx)
                api(AndroidX.lifecycle.liveDataKtx)

                api(AndroidX.dataStore)
                api(AndroidX.dataStore.preferences)

                api(AndroidX.hilt.navigationCompose)

                api(Google.dagger.hilt.android)
                api(Google.accompanist.coil)
                api(Google.accompanist.insets)
                api(Google.accompanist.insets.ui)
                api(Google.accompanist.pager)
                api(Google.accompanist.pager.indicators)
                api(Google.accompanist.systemuicontroller)

                api(Utils.multiplatformDataStoreSettings)
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