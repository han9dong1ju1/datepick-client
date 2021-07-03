plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.0"
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("dev.icerock.mobile.multiplatform-resources")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies"
        )
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "app.hdj.client" // required
    iosBaseLocalizationRegion = "ko" // optional, default "en"
}

kotlin {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }

    android()
    iosX64("ios")

    cocoapods {
        summary = "DatePick Multiplatform"
        homepage = "https://github.com/han9dong1ju1/DatePick"
        ios.deploymentTarget = "14.0"
        frameworkName = "app/hdj/shared/client/client"
        podfile = project.file("../../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:firebase"))
                api(KotlinX.coroutines.core)
                api(KotlinX.serialization.json)
                api(Ktor.client.core)
                api(Ktor.client.json)
                api(Ktor.client.serialization)
                api(Ktor.client.logging)
                api(Ktor.client.cio)
                api(Utils.kotlinxDateTime)

                api(MultiplatformSettings.core)
                api(MultiplatformSettings.coroutines)
                api(MultiplatformSettings.serialization)
                api(MultiplatformSettings.test)

                api(Utils.mokoResource)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {

                api(AndroidX.compose.compiler)
                api(AndroidX.compose.material)
                api(AndroidX.compose.material.icons.core)
                api(AndroidX.compose.material.icons.extended)
                api(AndroidX.compose.foundation)
                api(AndroidX.compose.runtime)
                api(AndroidX.compose.ui)
                api(AndroidX.compose.ui.tooling)
                api(AndroidX.appCompat)
                api(AndroidX.constraintLayoutCompose)

                api(AndroidX.paging.commonKtx)
                api(AndroidX.paging.compose)

                api(AndroidX.navigation.compose)
                api(AndroidX.navigation.commonKtx)
                api(AndroidX.navigation.uiKtx)

                api(AndroidX.activity)

                api(AndroidX.security.cryptoKtx)

                api(AndroidX.lifecycle.viewModelCompose)
                api(AndroidX.lifecycle.viewModelKtx)
                api(AndroidX.lifecycle.liveDataKtx)

                api(AndroidX.dataStore)
                api(AndroidX.dataStore.preferences)

                api(AndroidX.hilt.navigationCompose)

                api(JakeWharton.timber)

                api(Google.dagger.hilt.android)
                api(Google.accompanist.coil)
                api(Google.accompanist.insets)
                api(Google.accompanist.insets.ui)
                api(Google.accompanist.pager)
                api(Google.accompanist.pager.indicators)
                api(Google.accompanist.systemuicontroller)
                api(Google.accompanist.swiperefresh)
                api(Google.android.playServices.location)
                api(Google.android.play.coreKtx)

                api(MultiplatformSettings.datastore)

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Koin.core)
            }
        }
        val iosTest by getting
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

fun kapt(path: String) {
    configurations["kapt"].dependencies.add(project.dependencies.create(path))
}

dependencies {
    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
}
