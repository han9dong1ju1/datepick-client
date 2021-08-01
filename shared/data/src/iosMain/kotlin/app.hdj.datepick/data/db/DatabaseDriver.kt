package app.hdj.datepick.data.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriver {
    actual val driver : SqlDriver =
        NativeSqliteDriver(datepickSchema, name = "datepick-database.db")
}