package app.hdj.datepick.data.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriver constructor(context : Context) {
    actual val driver : SqlDriver =
        AndroidSqliteDriver(datepickSchema, context, name = "datepick-database.db")
}