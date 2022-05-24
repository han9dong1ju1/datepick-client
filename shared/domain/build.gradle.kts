plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
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

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(project(":shared:utils"))
            api(Utils.paging)
            implementation(KotlinX.coroutines.core)
            implementation(KotlinX.serialization.json)
            implementation(Ktor.client.core)
            implementation(Utils.kotlinxDateTime)
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
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    baseDefaultConfig()
}

kotlin {

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
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