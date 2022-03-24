pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "datepick-client"

plugins {
    id("de.fayard.refreshVersions") version "0.30.2"
////                            # available:"0.40.0"
////                            # available:"0.40.1"
}

include(":android:app")
include(":android:ui")

include(":shared:domain")
include(":shared:data")
include(":shared:presentation")
include(":shared:utils")
