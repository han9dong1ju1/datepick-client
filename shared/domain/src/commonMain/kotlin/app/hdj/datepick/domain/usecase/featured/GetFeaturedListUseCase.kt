package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetFeaturedListUseCase @Inject constructor(
    private val featuredRepository: FeaturedRepository
) {

    operator fun invoke() = featuredRepository.getFeaturedPage()

}