package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class UnregisterMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    fun execute(param: Param) = meRepository.unregister(param.type, param.reason)

    data class Param(
        val type: Int,
        val reason: String?
    )

}