package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class UnregisterMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    fun execute() = meRepository.unregister()

}