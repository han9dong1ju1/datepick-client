package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class GetFeaturedListUseCase @Inject constructor(
    private val featuredRepository: FeaturedRepository
) : UseCase<Unit, Flow<LoadState<List<Featured>>>> {

    override operator fun invoke(input : Unit) = featuredRepository.getFeatured()

}