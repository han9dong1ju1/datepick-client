package app.hdj.datepick.data.repository

import app.hdj.datepick.FeaturedEntity
import app.hdj.datepick.data.api.FeaturedApi
import app.hdj.datepick.data.datastore.FeaturedDataStore
import app.hdj.datepick.data.mapper.FeaturedMapper
import app.hdj.datepick.data.mapper.FeaturedMapper.asDomain
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import app.hdj.datepick.utils.date.isPassedDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class FeaturedRepositoryImp @Inject constructor(
    private val api: FeaturedApi,
    private val dataStore: FeaturedDataStore
) : FeaturedRepository, Mapper<FeaturedEntity, Featured> by FeaturedMapper {

    override fun getTopFeaturedList() = flow<LoadState<List<Featured>>> {
        emitState {
            val response = api.getPagedFeatured(0, 10, true, null)
            val data = response.data.content
            dataStore.saveAll(data.mapTable())
            data
        }
    }

    override fun getById(id: Long) = flow {
        emitState {
            val cached = dataStore.runCatching { get(id) }.getOrNull()
            if (cached != null && !(cached.cachedAt isPassedDay 7)) {
                cached.asDomain()
            } else {
                api.getFeaturedDetail(id).data
            }
        }
    }

}