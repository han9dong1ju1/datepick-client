import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(project(":shared"))
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
}

android {
    compileSdkPreview = Properties.androidCompileSDK

    defaultConfig {
        applicationId = Properties.androidPackageName
        minSdk = Properties.androidMinSDK
        targetSdkPreview = Properties.androidTargetSDK
        versionCode = Properties.androidAppVersionCode
        versionName = Properties.androidAppVersionName
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