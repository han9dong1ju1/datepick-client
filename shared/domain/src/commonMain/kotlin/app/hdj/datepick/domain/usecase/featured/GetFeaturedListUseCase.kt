package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class GetFeaturedListUseCase @Inject constructor(
    private val featuredRepository: FeaturedRepository
) {

    fun execute() = featuredRepository.getFeatured()

}