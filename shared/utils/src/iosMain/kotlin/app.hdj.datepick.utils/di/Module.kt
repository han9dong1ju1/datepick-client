package app.hdj.datepick.utils.di

import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.location.LocationTracker
import org.koin.dsl.module
import platform.Foundation.NSBundle

val utilsModule = module {
    single {
        AppInfo(
            "app.hdj.datepick.iosApp",
            Platform.isDebugBinary,
            AppInfo.Os.iOS,
            NSBundle.mainBundle.infoDictionary!!["CFBundleName"] as String,
        )
    }
    single {
        LocationTracker()
    }
}