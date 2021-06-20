import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":android:ui"))
    implementation(project(":shared:client"))
    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
    implementation(Google.dagger.hilt.android)
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