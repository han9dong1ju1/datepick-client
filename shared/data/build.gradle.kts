plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("com.squareup.sqldelight")
}

version = "1.0"

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.time.ExperimentalTime",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.OptIn",
            "-Xallow-jvm-ir-dependencies"
        )
    }
}

sqldelight {
    database("DatePickDatabase") {
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
                api(Ktor.client.core)
                api(Ktor.client.serialization)
                api(Ktor.client.logging)
                api(Utils.kotlinxDateTime)
                api(Square.sqlDelight.coroutinesExtensions)
                api(MultiplatformSettings.core)
                api(MultiplatformSettings.coroutines)
                api(MultiplatformSettings.serialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(Mokk.common)
                implementation(Ktor.client.tests)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Google.dagger.hilt.android)
                api(Square.sqlDelight.drivers.android)
                api(Ktor.client.okHttp)
                api(MultiplatformSettings.datastore)
                api(AndroidX.dataStore.core)
                api(AndroidX.dataStore.preferences)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(Mokk.core)
                implementation(Testing.junit4)
                implementation(Testing.junit.api)
                implementation(Testing.junit.engine)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Koin.core)
                implementation(Ktor.client.darwin)
                implementation(Square.sqlDelight.drivers.native)
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkPreview = Properties.androidCompileSDK

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

fun kapt(path: String) {
    configurations["kapt"].dependencies.add(project.dependencies.create(path))
}

dependencies {
    kapt(AndroidX.paging.runtimeKtx)
    kapt(AndroidX.navigation.runtimeKtx)
    kapt(AndroidX.hilt.compiler)
    kapt(Google.dagger.hilt.compiler)
}
