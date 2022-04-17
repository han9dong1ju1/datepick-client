package app.hdj.datepick.domain.usecase.district

import app.hdj.datepick.domain.repository.DistrictRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetDistrictsUseCase @Inject constructor(private val repository: DistrictRepository) {

    operator fun invoke() = repository.getDistricts()

}