package app.hdj.datepick.utils.di

import app.hdj.datepick.utils.AppInfo
import org.koin.dsl.module

val utilsModule = module {
    single {
        AppInfo(
            "app.hdj.datepick.iosApp",
            Platform.isDebugBinary,
            AppInfo.Os.iOS
        )
    }
}