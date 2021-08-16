package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.featured.FeaturedDetail
import kotlinx.coroutines.flow.Flow

interface FeaturedRepository {

    fun getFeatured(): Flow<LoadState<List<Featured>>>

    fun getFeaturedDetail(id: Long): Flow<LoadState<FeaturedDetail>>

}