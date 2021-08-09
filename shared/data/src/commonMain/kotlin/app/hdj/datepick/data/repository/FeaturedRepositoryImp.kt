package app.hdj.datepick.data.repository

import app.hdj.datepick.FeaturedEntity
import app.hdj.datepick.data.api.FeaturedApi
import app.hdj.datepick.data.datastore.FeaturedDataStore
import app.hdj.datepick.data.mapper.FeaturedMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.featured.FeaturedDetail
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@Singleton
class FeaturedRepositoryImp @Inject constructor(
    private val api: FeaturedApi,
    private val dataStore: FeaturedDataStore
) : FeaturedRepository, Mapper<FeaturedEntity, Featured> by FeaturedMapper {

    override fun getFeatured() = flow<StateData<List<Featured>>> {
        // 이전 캐시를 먼저 방출해서 보여줍니다.
        dataStore.findAllCached().collect { list ->
            if (list.isNotEmpty())
                emit(StateData.success(list.mapDomain()))
        }

        emitState(onSuccess = { list -> dataStore.saveAll(list.mapTable()) }) {
            api.getFeatured().data
        }
    }

    override fun getFeaturedDetail(id: String) = flow<StateData<FeaturedDetail>> {
        emitState {
            api.getFeaturedDetail(id).data
        }
    }

}