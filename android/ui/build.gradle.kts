import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":shared:utils"))

    implementation(KotlinX.serialization.core)
    implementation(KotlinX.serialization.json)
    api(AndroidX.appCompat)
    api(AndroidX.activity.ktx)
    api(Google.android.material)
    api(AndroidX.paging.compose)
    api(AndroidX.navigation.compose)
    api(AndroidX.activity.compose)
    api(AndroidX.compose.compiler)
    api(AndroidX.compose.animation)
    api(AndroidX.compose.material.icons.core)
    api(AndroidX.compose.material.icons.extended)
    api(AndroidX.compose.foundation)
    api(AndroidX.compose.runtime)
    api(AndroidX.compose.ui)
    api(AndroidX.compose.ui.tooling)
    api(AndroidX.constraintLayout.compose)
    api(COIL.compose)
    api(COIL.composeBase)
    api(Google.accompanist.insets)
    api(Google.accompanist.insets.ui)
    api(Google.accompanist.pager)
    api(Google.accompanist.pager.indicators)
    api(Google.accompanist.systemuicontroller)
    api(Google.accompanist.swiperefresh)
    api(Google.accompanist.placeholderMaterial)
    api(Google.accompanist.navigation.animation)
    api(Google.accompanist.navigation.material)
    api(Google.accompanist.flowlayout)
    api(Google.android.maps)
    api(Google.android.mapsUtils)

    api(AndroidX.core.splashscreen)

    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
    implementation(Google.dagger.hilt.android)
}

android {
    compileSdk = Properties.androidCompileSDK

    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdk = Properties.androidTargetSDK
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