package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import com.kuuurt.paging.multiplatform.Pager
import kotlinx.coroutines.flow.Flow

interface FeaturedRepository {

    fun getFeaturedPage() : Pager<Long, Featured>

    fun getTopFeaturedList(): Flow<List<Featured>>

    fun getById(id: Long): Flow<Featured>

}