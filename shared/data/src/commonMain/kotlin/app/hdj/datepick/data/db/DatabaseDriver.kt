package app.hdj.datepick.data.db

import app.hdj.datepick.DatepickDatabase
import com.squareup.sqldelight.db.SqlDriver

internal val datepickSchema : SqlDriver.Schema = DatepickDatabase.Schema

expect class DatabaseDriver {
    val driver : SqlDriver
}