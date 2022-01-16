plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("com.google.devtools.ksp")
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

    val iosTarget: (String, org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit) -> org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget = when {
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
            baseName = "domain"
        }
        podfile = project.file("../../iosApp/Podfile")
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(project(":shared:utils"))
            implementation(KotlinX.coroutines.core)
            implementation(KotlinX.serialization.json)
            implementation(Ktor.client.core)
            implementation(Utils.kotlinxDateTime)
            implementation(Firebase.multiplatform.auth)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
        sourceSets["androidMain"].dependencies {
            kapt(AndroidX.paging.runtimeKtx)
            kapt(AndroidX.navigation.runtimeKtx)
            kapt(AndroidX.hilt.compiler)
            kapt(Google.dagger.hilt.compiler)
            implementation(Google.dagger.hilt.android)
        }
        sourceSets["androidTest"].dependencies {
            implementation(kotlin("test-junit"))
            implementation(Testing.junit4)
        }
        sourceSets["iosMain"].dependencies {
            implementation(Koin.core)
        }
        sourceSets["iosTest"].dependencies {

        }
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

kotlin {

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
        binaries.all {
            binaryOptions["memoryModel"] = "experimental"
        }
    }

}