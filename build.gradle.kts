buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("com.android.tools.build:gradle:7.1.0-alpha02")
        classpath(Google.playServicesGradlePlugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(Firebase.`no-BoM`.crashlyticsGradlePlugin)
        classpath(Utils.mokoResourcePlugins)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}