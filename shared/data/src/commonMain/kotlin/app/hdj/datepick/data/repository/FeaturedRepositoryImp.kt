package app.hdj.datepick.data.repository

import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.FeaturedMapper
import app.hdj.datepick.data.model.response.featured.FeaturedResponse
import app.hdj.datepick.data.remote.api.FeaturedApi
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.flow

@Singleton
class FeaturedRepositoryImp @Inject constructor(
    private val api: FeaturedApi
) : FeaturedRepository, Mapper<FeaturedResponse, Featured> by FeaturedMapper {

    override fun getTopFeaturedList() = flow<LoadState<List<Featured>>> {
        emit(loading())
        api.runCatching { getPagedFeatured(0, 10, true, null) }
            .onFailure { emit(LoadState.failed(it)) }
            .onSuccess { emit(success(it.data.content.mapDomain())) }
    }

    override fun getById(id: Long) = flow<LoadState<Featured>> {
        emit(loading())
        api.runCatching { getFeaturedDetail(id) }
            .onFailure { emit(LoadState.failed(it)) }
            .onSuccess { emit(success(it.data.dataToDomain())) }
    }

}