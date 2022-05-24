import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.1"
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

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "15.0"
        framework {
            baseName = "presentation"
        }
        podfile = project.file("../../iosApp/Podfile")
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(project(":shared:data"))
            implementation(project(":shared:utils"))
            implementation(project(":shared:domain"))
            implementation(KotlinX.coroutines.core)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
        sourceSets["androidMain"].dependencies {
            implementation(Google.dagger.hilt.android)
            api(JakeWharton.timber)
            implementation(AndroidX.lifecycle.viewModelKtx)
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
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    baseDefaultConfig()
}

kotlin {

    targets.withType(KotlinNativeTarget::class.java) {
        binaries.all {
            binaryOptions["memoryModel"] = "experimental"
        }
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-Xopt-in=kotlin.RequiresOptIn",
                    "-Xopt-in=kotlin.OptIn",
                    "-Xopt-in=kotlinx.coroutines.FlowPreview",
                    "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
                    "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                    "-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi",
                    "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-Xopt-in=coil.annotation.ExperimentalCoilApi",
                )
            }
        }
    }

}