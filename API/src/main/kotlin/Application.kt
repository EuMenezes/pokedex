import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import route.pokemonExemplo

fun main() {
    embeddedServer(Netty, port = 8080) {
        registerCustomerRoutes()

        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
        }
    }.start(wait = true)
}

fun Application.registerCustomerRoutes() {
    routing {
        pokemonExemplo()
    }
}