package app.hdj.shared.client

import app.hdj.shared.client.data.ApiClient
import app.hdj.shared.client.data.AppleAppDataStore
import app.hdj.shared.client.data.api.Authenticator
import app.hdj.shared.client.data.api.AuthenticatorImp
import app.hdj.shared.client.data.datastore.AppDataStore
import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
private fun dataModule(isDebugMode: Boolean = Platform.isDebugBinary) = module {

    single { ApiClient.createHttpClient(isDebugMode, get()) }
    single<AppDataStore> { AppleAppDataStore(get()) }

    single { AppleSettings(NSUserDefaults.new()!!).toFlowSettings() }

}

private val domainModule = module {

    single {  }

}

private val utilModule = module {

    single<Authenticator> { AuthenticatorImp(get<AppDataStore>()) }

}

fun initKoin(isDebugMode: Boolean): KoinApplication = startKoin {
    modules(
        dataModule(isDebugMode),
        domainModule,
        utilModule,
    )
}

fun Koin.get(objCProtocol: ObjCProtocol): Any {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return get(kClazz)
}