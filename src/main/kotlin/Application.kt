import io.ktor.server.application.*
import plugins.configureMonitoring
import plugins.configureRouting
import plugins.configureSerialization
import plugins.configureSockets

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
