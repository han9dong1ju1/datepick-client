package app.hdj.datepick.data.utils

import app.hdj.datepick.data.api.ApiPagingData

actual class PlatformPagingSource<T : Any> actual constructor(call: suspend (Int) -> ApiPagingData<T>)