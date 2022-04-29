package app.hdj.datepick.data.repository

import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.FeaturedMapper
import app.hdj.datepick.data.model.response.featured.FeaturedResponse
import app.hdj.datepick.data.remote.api.FeaturedApi
import app.hdj.datepick.data.remote.map
import app.hdj.datepick.data.utils.createPager
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import com.kuuurt.paging.multiplatform.Pager
import kotlinx.coroutines.flow.flow

@Singleton
class FeaturedRepositoryImp @Inject constructor(
    @Named("mocked") private val api: FeaturedApi
) : FeaturedRepository, Mapper<FeaturedResponse, Featured> by FeaturedMapper {

    override fun getFeaturedPage() : Pager<Long, Featured> = createPager { page ->
        val response = api.getPagedFeatured(page = page, size = 10, false, null)
        response.data.map { it.asDomain() }
    }

    override fun getTopFeaturedList() = flow {
        val response = api.getPagedFeatured(0, 5, false, null)
        emit(response.data.content.mapDomain())
    }

    override fun getById(id: Long) = flow {
        val response = api.getFeaturedDetail(id)
        emit(response.data.asDomain())
    }

}