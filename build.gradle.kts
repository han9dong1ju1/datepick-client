
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
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath(Google.playServicesGradlePlugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(Firebase.`no-BoM`.crashlyticsGradlePlugin)
        classpath(Utils.mokoResourcePlugins)
    }
}

plugins {
    val kotlinVersion = "1.5.10"
    val springBootVersion = "2.5.1"
    val springBootDependencyManagementVersion = "1.0.11.RELEASE"

    kotlin("jvm") version kotlinVersion apply false
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false
    id("org.springframework.boot") version springBootVersion apply false
    id("io.spring.dependency-management") version springBootDependencyManagementVersion apply false
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