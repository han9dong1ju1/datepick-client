@file:Suppress("unused", "UnstableApiUsage")

object Koin {

    const val core = "io.insert-koin:koin-core:_"

}

object MultiplatformSettings {
    const val core = "com.russhwolf:multiplatform-settings:_"

    const val serialization = "com.russhwolf:multiplatform-settings-serialization:_"

    const val datastore = "com.russhwolf:multiplatform-settings-datastore:_"

    const val test = "com.russhwolf:multiplatform-settings-test:_"

    const val coroutines = "com.russhwolf:multiplatform-settings-coroutines:_"
}

object Multiplatform {
    const val auth = "dev.gitlive:firebase-auth:_"
    const val firestore = "dev.gitlive:firebase-firestore:_"
}

val AndroidX.Core.splashscreen get() = "androidx.core:core-splashscreen:1.0.0-alpha01"
val Firebase.multiplatform get() = Multiplatform

val Google.Android.maps get() = "com.google.maps.android:maps-ktx:_"
val Google.Accompanist.navigation get() = Navigation

object Navigation {
    const val animation = "com.google.accompanist:accompanist-navigation-animation:_"
    const val material = "com.google.accompanist:accompanist-navigation-material:_"
}

object Mokk {
    const val common = "io.mockk:mockk-common:_"
}

object Utils {

    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:_"

    const val mokoParcel = "dev.icerock.moko:parcelize:_"

    const val mokoResource = "dev.icerock.moko:resources:_"

    const val mokoResourcePlugins = "dev.icerock.moko:resources-generator:_"

    const val imageCompressor = "id.zelory:compressor:_"

    const val composeCollapsingToolbarLayout = "me.onebone:toolbar-compose:_"

    const val composeCoil = "io.coil-kt:coil-compose:_"

}