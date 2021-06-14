package app.hdj.datepick.server

import kotlinx.coroutines.delay
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.coRouter

@SpringBootApplication
class ServerApplication {

    @Bean
    fun routes() = coRouter {
        GET("/") {
            delay(1000)
            status(200).bodyValue("Hello World").awaitSingle()
        }
    }

}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
