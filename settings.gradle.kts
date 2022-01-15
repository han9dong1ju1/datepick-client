pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "datepick-client"

plugins {
    id("de.fayard.refreshVersions") version "0.30.1"
////                            # available:"0.30.2"
}

include(":android:app")
include(":android:ui")

include(":shared:domain")
include(":shared:data")
include(":shared:utils")
