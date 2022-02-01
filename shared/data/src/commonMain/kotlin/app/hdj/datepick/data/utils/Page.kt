package app.hdj.datepick.data.utils

import app.hdj.datepick.data.api.PagingResponse
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.utils.coroutines.IO
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun <V : Any> createPagedDataFlow(
    initialKey: Long = 0L,
    pagingConfig : PagingConfig = PagingConfig(
        pageSize = 30,
        enablePlaceholders = false
    ),
    call : suspend (Long) -> PagingResponse<V>?
) = Pager(
    clientScope = CoroutineScope(Dispatchers.IO),
    config = pagingConfig,
    initialKey = initialKey,
    getItems = { nextPage, _ ->
        val apiResponse = call(nextPage)
        val currentKey = apiResponse?.currentPage ?: 0

        PagingResult(
            items = apiResponse?.content ?: emptyList(),
            currentKey = currentKey,
            nextKey = { if (apiResponse != null) currentKey + 1 else null },
            prevKey = { if (apiResponse != null) currentKey - 1 else null }
        )
    }
).pagingData