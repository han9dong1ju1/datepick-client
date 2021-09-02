import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("kapt")
}

version = "1.0"

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

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.0"
        frameworkName = "utils"
        podfile = project.file("../../iosApp/Podfile")
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            api(Firebase.multiplatform.auth)
            api(Utils.kotlinxDateTime)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
        sourceSets["androidMain"].dependencies {
            implementation(Google.dagger.hilt.android)
            api(JakeWharton.timber)
            kapt(AndroidX.paging.runtimeKtx)
            kapt(AndroidX.navigation.runtimeKtx)
            kapt(AndroidX.hilt.compiler)
            kapt(Google.dagger.hilt.compiler)
        }
        sourceSets["androidTest"].dependencies {
            implementation(kotlin("test-junit"))
            implementation(Testing.junit4)
        }
        sourceSets["iosMain"].dependencies {
            implementation(Koin.core)
        }
        sourceSets["iosTest"].dependencies { }
    }
}

android {
    compileSdk = Properties.androidCompileSDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdk = Properties.androidTargetSDK
    }
}

fun kapt(path: Any) {
    configurations["kapt"].dependencies.add(project.dependencies.create(path))
}

fun ksp(path: Any) {
    configurations["ksp"].dependencies.add(project.dependencies.create(path))
}