package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import kotlinx.coroutines.flow.Flow

interface FeaturedRepository {

    fun getTopFeaturedList(): Flow<List<Featured>>

    fun getById(id: Long): Flow<Featured>

}