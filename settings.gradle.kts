pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "DatePick"

plugins {
    id("de.fayard.refreshVersions") version "0.10.0"
}

include(":android:app")
include(":android:ui")
include(":shared")
include(":sdk:firebase")
include(":server")
