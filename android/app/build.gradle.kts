import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":android:ui"))
    implementation(project(":shared:utils"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:data"))
    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    ksp(AndroidX.hilt.compiler)
    ksp(Google.dagger.hilt.compiler)
    implementation(Google.dagger.hilt.android)

    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)

    implementation(AndroidX.paging.commonKtx)
    implementation(AndroidX.navigation.commonKtx)
    implementation(AndroidX.navigation.uiKtx)

    implementation(AndroidX.activity)

    implementation(AndroidX.security.cryptoKtx)

    implementation(AndroidX.lifecycle.viewModelCompose)
    implementation(AndroidX.lifecycle.viewModelKtx)

    implementation(AndroidX.dataStore)
    implementation(AndroidX.dataStore.preferences)

    implementation(AndroidX.hilt.navigationCompose)

    implementation(Google.dagger.hilt.android)
    implementation(Google.android.playServices.location)
    implementation(Google.android.playServices.auth)
    implementation(Google.android.play.coreKtx)

    implementation(project.dependencies.platform(Google.firebase.bom))
    implementation(Google.firebase.cloudMessagingKtx)
    implementation(Google.firebase.analyticsKtx)
    implementation(Google.firebase.crashlyticsKtx)

}

android {
    compileSdk = Properties.androidCompileSDK

    defaultConfig {
        applicationId = Properties.androidPackageName
        minSdk = Properties.androidMinSDK
        targetSdk = Properties.androidTargetSDK
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

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.OptIn",
            "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-Xopt-in=coil.annotation.ExperimentalCoilApi",
            "-Xallow-jvm-ir-dependencies"
        )
    }
}