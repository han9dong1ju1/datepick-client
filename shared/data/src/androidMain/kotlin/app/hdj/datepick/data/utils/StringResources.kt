package app.hdj.datepick.data.utils

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.desc

fun Context.res(resource: StringResource) = resource.desc().toString(this)