buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.android.tools.build:gradle:7.1.0-alpha10")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.0")
        classpath(Google.playServicesGradlePlugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(Square.sqlDelight.gradlePlugin)
        classpath(Firebase.crashlyticsGradlePlugin)
        classpath(Utils.mokoResourcePlugins)
    }
}

allprojects {

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlin.OptIn",
                "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi",
                "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
                "-Xopt-in=coil.annotation.ExperimentalCoilApi",
            )
        }
    }
}

tasks.register("clean", Delete::class) {
    rootProject.subprojects {
        delete(buildDir)
    }
    delete(rootProject.buildDir)
}