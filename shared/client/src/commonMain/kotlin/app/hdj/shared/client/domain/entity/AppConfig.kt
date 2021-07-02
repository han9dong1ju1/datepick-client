package app.hdj.shared.client.domain.entity

data class AppConfig(
    val androidPackageName: String? = null,
    val iosBundleId : String? = null,
    val version : String
)