package app.hdj.datepick.data.repository

import app.hdj.datepick.data.mapper.DistrictMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.model.response.district.DistrictResponse
import app.hdj.datepick.data.remote.api.DistrictApi
import app.hdj.datepick.domain.model.district.District
import app.hdj.datepick.domain.repository.DistrictRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.flow

@Singleton
class DistrictRepositoryImp @Inject constructor(
    @Named("mocked") private val districtApi: DistrictApi
) : DistrictRepository, Mapper<DistrictResponse, District> by DistrictMapper {

    override fun getDistricts() = flow {
        val response = districtApi.getDistricts()
        emit(response.data.mapDomain())
    }

}