package app.hdj.datepick.utils.date

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
infix fun Long.isPassedDay(day: Long) =
    Clock.System.now() - Instant.fromEpochMilliseconds(this) < day.days