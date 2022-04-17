package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

@Singleton
class GetFeaturedListUseCase @Inject constructor(
    private val featuredRepository: FeaturedRepository
) : UseCase<Unit, Flow<LoadState<List<Featured>>>> {

    override fun invoke(input: Unit): Flow<LoadState<List<Featured>>> =
        featuredRepository.getTopFeaturedList()

}