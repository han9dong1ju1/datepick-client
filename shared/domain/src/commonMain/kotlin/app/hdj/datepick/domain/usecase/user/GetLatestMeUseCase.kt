package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetLatestMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    operator fun invoke() = meRepository.fetch()

}