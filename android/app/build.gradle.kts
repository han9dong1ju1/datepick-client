import de.fayard.refreshVersions.core.versionFor
import java.util.Properties
import Properties as AppProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {
    implementation(project(":android:ui"))
    implementation(project(":shared:utils"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:data"))
    implementation(project(":shared:presentation"))

    implementation(Google.dagger.hilt.android)
    implementation(KotlinX.coroutines.playServices)
    implementation(KotlinX.serialization.json)

    implementation(AndroidX.core)
    implementation(AndroidX.core.splashscreen)

    implementation(AndroidX.glanceAppWidget)

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

    implementation(AndroidX.paging.compose)
    implementation(AndroidX.paging.runtimeKtx)

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

    implementation(Utils.jsoup)
    implementation(Utils.imageCompressor)
    implementation(Utils.markdown)
    implementation(Utils.markdownStrikethrough)
    implementation(Utils.markdownTables)
    implementation(Utils.markdownHtml)
    implementation(Utils.markdownCoil)
    implementation(Utils.markdownLinkify)
    implementation(Utils.qrGenerator)

    implementation(ComposeDestination.core)
    implementation(ComposeDestination.animationsCore)
    ksp(ComposeDestination.ksp)

    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
}

fun createDebugReleaseNote(): String {
    val releaseNote = File("${project.rootDir}/fastlane/metadata/android/ko-KR/changelogs/debug-release-notes.txt")
    releaseNote.writeText(
        "Version: \tVer ${AppProperties.androidAppVersionName}-$gitDescribe-DEBUG\n\n" +
                "Branch: \t$gitBranch\n\n" +
                "Developer: \t$developer\n\n" +
                "Project Development Overview\n" +
                "\tTask\n\t\t$commitList"
    )
    releaseNote.createNewFile()
    return releaseNote.absolutePath
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

    buildTypes {
        getByName("debug") {
            createDebugReleaseNote()
            versionNameSuffix = "-$gitDescribe-DEBUG"
        }

        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
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