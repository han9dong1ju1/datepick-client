plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    implementation("com.android.tools.build:gradle:7.4.0-alpha02")
    implementation("com.squareup:javapoet:1.13.0")
}

repositories {
    google()
    mavenCentral()
}