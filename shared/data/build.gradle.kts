plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("com.squareup.sqldelight")
}

version = "1.0"

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.time.ExperimentalTime",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.OptIn",
            "-Xallow-jvm-ir-dependencies"
        )
    }
}

sqldelight {
    database("DatepickDatabase") {
        packageName = "app.hdj.datepick"
    }
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
        frameworkName = "data"
        podfile = project.file("../../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:utils"))
                implementation(project(":shared:domain"))
                implementation(KotlinX.coroutines.core)
                implementation(KotlinX.serialization.core)
                implementation(KotlinX.serialization.json)
                implementation(Ktor.client.core)
                implementation(Ktor.client.cio)
                implementation(Ktor.client.serialization)
                implementation(Ktor.client.logging)
                implementation(MultiplatformSettings.core)
                implementation(MultiplatformSettings.coroutines)
                implementation(MultiplatformSettings.serialization)
                implementation(Utils.kotlinxDateTime)
                implementation(Square.sqlDelight.coroutinesExtensions)
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
                implementation(Google.dagger.hilt.android)
                implementation(Square.sqlDelight.drivers.android)
                implementation(MultiplatformSettings.datastore)
                implementation(AndroidX.dataStore.core)
                implementation(AndroidX.dataStore.preferences)
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
                implementation(Square.sqlDelight.drivers.native)
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
