import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("kapt")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.codingfeline.buildkonfig")
}

version = "1.0"

sqldelight {
    database("DatePickDatabase") {
        packageName = "app.hdj.datepick"
    }
}

android {
    configurations {
        create("mockImplementation")
        create("mockCompileOnly")
        create("mockRuntimeOnly")
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

buildkonfig {
    packageName = "app.hdj.datepick.android"
    defaultConfigs {
        buildConfigField(STRING, "kakaoApiKey", "71ee2c3a305758fff12755acedc14f36")
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
            api(Ktor.client.auth)
            api(Ktor.client.mock)
            api(Square.sqlDelight.extensions.coroutines)
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
            implementation(AndroidX.security.cryptoKtx)
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

    baseDefaultConfig()

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