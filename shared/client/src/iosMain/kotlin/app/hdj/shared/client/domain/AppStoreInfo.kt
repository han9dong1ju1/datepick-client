package app.hdj.shared.client.domain

import app.hdj.shared.client.domain.Result

data class AppStoreInfo(
    val resultCount: Int,
    val results: List<Result>
)