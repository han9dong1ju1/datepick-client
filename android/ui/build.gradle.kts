import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies"
        )
    }
}

dependencies {
    implementation(project(":shared:utils"))

    api(AndroidX.appCompat)
    api(AndroidX.activityKtx)
    api(Google.android.material)
    api(AndroidX.paging.compose)
    api(AndroidX.navigation.compose)
    api(AndroidX.activity.compose)
    api(AndroidX.compose.compiler)
    api(AndroidX.compose.material)
    api(AndroidX.compose.material.icons.core)
    api(AndroidX.compose.material.icons.extended)
    api(AndroidX.compose.foundation)
    api(AndroidX.compose.runtime)
    api(AndroidX.compose.ui)
    api(AndroidX.compose.ui.tooling)
    api(AndroidX.constraintLayoutCompose)
    api(Utils.composeCollapsingToolbarLayout)
    api(Utils.composeCoil)
    api(Google.accompanist.insets)
    api(Google.accompanist.insets.ui)
    api(Google.accompanist.pager)
    api(Google.accompanist.pager.indicators)
    api(Google.accompanist.systemuicontroller)
    api(Google.accompanist.swiperefresh)
    api(Google.accompanist.navigation.animation)
    api(Google.accompanist.navigation.material)
    api(Google.android.maps)
    api(Google.android.mapsUtils)

    api(AndroidX.core.splashscreen)

    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
    kapt(Google.dagger.hilt.android)
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