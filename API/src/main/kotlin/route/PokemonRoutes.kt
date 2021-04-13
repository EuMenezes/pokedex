package route

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import model.Pokemon
import model.adapter.Response
import model.adapter.ResponseOk
import util.tryCatch

val listaPokemonTeste = mutableListOf(
    Pokemon(1, "Teste 1"),
    Pokemon(2, "Teste 2")
)

fun Route.pokemonExemplo() {
    route("/pokemonExemplo") {
        get {
            call.respond(
                tryCatch(this@route) {
                    if (listaPokemonTeste.isNotEmpty()) Response(listaPokemonTeste)
                    else throw NotFoundException("Dados Não encontrados")
                }
            )
        }

        get("{id}") {
            try {
                call.respond(tryCatch(this@route) {
                    val id = call.parameters["id"]?.toIntOrNull() ?: throw BadRequestException("Cabeçalho mal formado")
                    val retorno = listaPokemonTeste.firstOrNull { f ->
                        f.id == id
                    } ?: throw NotFoundException("Id $id não foi encontrado")

                    Response(retorno)
                })
            } catch (t: Throwable) {
                call.respond(t.message ?: "")
            }

        }

        post {
            val customer = call.receive<Pokemon>()
            listaPokemonTeste.add(customer)
            call.respond(ResponseOk())
        }

        delete("{id}") {
            call.respond(
                tryCatch(this@route) {
                    val id = call.parameters["id"] ?: throw BadRequestException("Cabeçalho mal formado")

                    if (listaPokemonTeste.removeIf { it.id == id.toInt() }) ResponseOk()
                    else throw NotFoundException("Id $id não foi encontrado")
                }
            )
        }
    }
}