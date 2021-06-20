import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    implementation(project(":shared:client"))

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

android {
    compileSdkPreview = Properties.androidCompileSDK

    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdkPreview = Properties.androidTargetSDK
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.ui)
    }

}