import de.fayard.refreshVersions.core.versionFor
import java.util.Properties
import java.io.ByteArrayOutputStream
import Properties as AppProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

dependencies {
    implementation(project(":android:ui"))
    implementation(project(":shared:utils"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:data"))
    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
    implementation(Google.dagger.hilt.android)
    implementation(KotlinX.coroutines.playServices)

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

    implementation(platform(Google.firebase.bom))
    implementation(Google.firebase.cloudMessagingKtx)
    implementation(Google.firebase.analyticsKtx)
    implementation(Google.firebase.crashlyticsKtx)
    implementation(Google.firebase.dynamicLinksKtx)

}

val gitDescribe: String by lazy {
    val stdout = ByteArrayOutputStream()
    rootProject.exec {
        executable("/bin/sh")
        args("-c", "git rev-parse --short HEAD")
        standardOutput = stdout
    }
    val commit = stdout.toString().trim()
    commit
}

android {
    compileSdk = AppProperties.androidCompileSDK

    defaultConfig {
        applicationId = AppProperties.androidPackageName
        minSdk = AppProperties.androidMinSDK
        targetSdk = AppProperties.androidTargetSDK
        versionCode = AppProperties.androidAppVersionCode
        versionName = AppProperties.androidAppVersionName
    }

    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-$gitDescribe-DEBUG"
        }

        getByName("release") {
            isMinifyEnabled = true
        }
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "debug"
            keyPassword = "android"
            storeFile = file("${project.rootDir.absolutePath}/keystore/debug.keystore")
            storePassword = "android"
        }

        create("release") {
            val keystoreProperties = Properties().apply {
                load(file("${project.rootDir.absolutePath}/keystore/keystore").inputStream())
            }
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.ui)
    }

}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
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