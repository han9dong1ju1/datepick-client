package app.hdj.datepick.data.repository

import app.hdj.datepick.FeaturedEntity
import app.hdj.datepick.data.api.FeaturedApi
import app.hdj.datepick.data.datastore.FeaturedDataStore
import app.hdj.datepick.data.entity.featured.FeaturedResponse
import app.hdj.datepick.data.mapper.FeaturedMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import app.hdj.datepick.utils.date.isPassedDay
import kotlinx.coroutines.flow.flow

@Singleton
class FeaturedRepositoryImp @Inject constructor(
    private val api: FeaturedApi,
    private val dataStore: FeaturedDataStore
) : FeaturedRepository, Mapper<FeaturedEntity, FeaturedResponse, Featured> by FeaturedMapper {

    override fun getTopFeaturedList() = flow {
        emitState {
            val response = api.getPagedFeatured(0, 10, true, null)
            val data = response.data.content
            dataStore.saveAll(data.mapTable())
            data.mapDomain()
        }
    }

    override fun getById(id: Long) = flow {
        val cached = dataStore.runCatching { get(id) }.getOrNull()
        emitState(cached?.tableToDomain()) {
            if (cached != null && !(cached.cachedAt isPassedDay 7)) {
                cached.tableToDomain()
            } else {
                api.getFeaturedDetail(id).data.dataToDomain()
            }
        }
    }

}