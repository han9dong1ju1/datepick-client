@file:SuppressLint("AndroidGradlePluginVersion")
import android.annotation.SuppressLint

allprojects {
    group = "app.hdj"
    version = "1.0.0-SNAPSHOT"
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.android.tools.build:gradle:7.0.0")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:1.3.0")
        classpath(Google.playServicesGradlePlugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(Square.sqlDelight.gradlePlugin)
        classpath(Firebase.`no-BoM`.crashlyticsGradlePlugin)
        classpath(Utils.mokoResourcePlugins)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}