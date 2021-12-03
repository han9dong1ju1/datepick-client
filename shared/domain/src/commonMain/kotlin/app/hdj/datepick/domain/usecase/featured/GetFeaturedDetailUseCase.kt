package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.featured.FeaturedDetail
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class GetFeaturedDetailUseCase @Inject constructor(
    private val featuredRepository: FeaturedRepository
) : UseCase<Long, Flow<LoadState<FeaturedDetail>>> {

    override operator fun invoke(input: Long) = featuredRepository.getById(input)

}