package app.hdj.datepick.data.repository

import app.hdj.datepick.DatepickDatabase
import app.hdj.datepick.data.mapper.map
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.map

@Singleton
class PlaceRepositoryImp @Inject constructor(
    val database: DatepickDatabase
) : PlaceRepository {



}