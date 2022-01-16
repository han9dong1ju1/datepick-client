import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

version = "1.0"

sqldelight {
    database("DatePickDatabase") {
        packageName = "app.hdj.datepick"
    }
}

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}


kotlin {

    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "DatePick Multiplatform"
        homepage = "https://github.com/han9dong1ju1/DatePick"
        ios.deploymentTarget = "14.0"
        framework {
            baseName = "data"
        }
        podfile = project.file("../../iosApp/Podfile")
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(project(":shared:utils"))
            implementation(project(":shared:domain"))
            implementation(KotlinX.coroutines.core)
            implementation(KotlinX.serialization.json)
            api(Ktor.serializationKotlinx)
            api(Ktor.client.core)
            api(Ktor.client.contentNegotiation)
            api(Ktor.client.json)
            api(Ktor.client.logging)
            api(Square.sqlDelight.coroutinesExtensions)
            api(MultiplatformSettings.core)
            api(MultiplatformSettings.coroutines)
            api(MultiplatformSettings.serialization)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(Mokk.common)
            implementation(KotlinX.coroutines.test)
            implementation(Ktor.client.tests)
        }
        sourceSets["androidMain"].dependencies {
            implementation(Google.dagger.hilt.android)
            implementation(Square.sqlDelight.drivers.android)
            implementation(Ktor.client.okHttp)
            implementation(MultiplatformSettings.datastore)
            implementation(AndroidX.dataStore.core)
            implementation(AndroidX.dataStore.preferences)
            implementation(AndroidX.paging.commonKtx)
            kapt(AndroidX.paging.runtimeKtx)
            kapt(AndroidX.navigation.runtimeKtx)
            kapt(AndroidX.hilt.compiler)
            kapt(Google.dagger.hilt.compiler)
        }
        sourceSets["androidTest"].dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-junit"))
            implementation(Mokk.core)
            implementation(Testing.junit4)
            implementation(Testing.junit.jupiter.api)
            implementation(Testing.junit.jupiter.engine)
        }
        sourceSets["iosMain"].dependencies {
            implementation(Koin.core)
            implementation(Ktor.client.darwin)
            implementation(Square.sqlDelight.drivers.native)
        }
        sourceSets["iosTest"].dependencies {

        }
    }
}

android {
    compileSdk = Properties.androidCompileSDK

    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdk = Properties.androidTargetSDK
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/res")
        }
        getByName("test") {
            java.srcDirs("src/androidTest/kotlin")
            res.srcDirs("src/androidTest/res")
        }
    }
}