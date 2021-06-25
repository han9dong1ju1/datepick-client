package app.hdj.shared.client

import app.hdj.shared.client.data.createHttpClient
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

fun initKoin(isDebugMode: Boolean): KoinApplication = startKoin {
    modules(
        module {
            single { createHttpClient(isDebugMode) }
        }
    )
}

fun Koin.get(objCProtocol: ObjCProtocol): Any {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return get(kClazz)
}