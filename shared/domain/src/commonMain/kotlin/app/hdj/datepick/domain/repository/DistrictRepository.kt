package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.model.district.District
import kotlinx.coroutines.flow.Flow

interface DistrictRepository {

    fun getDistricts() : Flow<List<District>>

}