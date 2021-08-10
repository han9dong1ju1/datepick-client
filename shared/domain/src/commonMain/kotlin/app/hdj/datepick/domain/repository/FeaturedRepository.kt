package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.featured.FeaturedDetail
import kotlinx.coroutines.flow.Flow

interface FeaturedRepository {

    fun getFeatured(): Flow<StateData<List<Featured>>>

    fun getFeaturedDetail(id: String): Flow<StateData<FeaturedDetail>>

}