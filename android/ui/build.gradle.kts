import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    implementation(project(":shared:client"))

    implementation(AndroidX.compose.compiler)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.foundation)
    implementation(AndroidX.compose.runtime)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.appCompat)

    implementation(AndroidX.paging.commonKtx)
    implementation(AndroidX.paging.compose)
    kapt(AndroidX.paging.runtimeKtx)

    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.navigation.commonKtx)
    implementation(AndroidX.navigation.uiKtx)
    kapt(AndroidX.navigation.runtimeKtx)

    implementation(AndroidX.activity)

    implementation(AndroidX.security.cryptoKtx)

    implementation(AndroidX.lifecycle.viewModelCompose)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.liveDataKtx)

    kapt(AndroidX.hilt.compiler)
    implementation(AndroidX.hilt.navigationCompose)

    kapt(Google.dagger.hilt.compiler)
    implementation(Google.dagger.hilt.android)
    implementation(Google.accompanist.coil)
    implementation(Google.accompanist.insets)
    implementation(Google.accompanist.pager)
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