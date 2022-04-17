package app.hdj.datepick.data.mapper

import app.hdj.datepick.data.model.response.district.DistrictResponse
import app.hdj.datepick.domain.model.district.District
import app.hdj.datepick.utils.location.LatLng

object DistrictMapper : Mapper<DistrictResponse, District> {
    override fun DistrictResponse.asDomain(): District {
        return District(
            id = id,
            name = name,
            latLng = LatLng(latitude, longitude)
        )
    }
}