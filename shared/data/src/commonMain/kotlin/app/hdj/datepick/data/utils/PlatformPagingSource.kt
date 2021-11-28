package app.hdj.datepick.data.utils

import app.hdj.datepick.data.api.ApiPagingData

expect class PlatformPagingSource<T : Any>(
    call : suspend (Int) -> ApiPagingData<T>
)