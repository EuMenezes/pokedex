package route

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import model.Pokemon
import model.adapter.Response
import model.adapter.ResponseList
import model.adapter.ResponseOk

val listaPokemonTeste = mutableListOf(
    Pokemon(1, "Teste 1"),
    Pokemon(2, "Teste 2")
)

fun Route.pokemonRouting() {
    route("/pokemon") {
        get {
            if (listaPokemonTeste.isNotEmpty()) call.respond(ResponseList(listaPokemonTeste))
            else call.respond(ResponseOk().also {
                it.ok = false
                it.exception = NotFoundException("Dados Não encontrados")
            })
        }

        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(ResponseOk().also {
                it.ok = false
                it.exception = BadRequestException("Cabeçalho mal formado")
            })

            val retorno = listaPokemonTeste.firstOrNull { f -> f.id == id } ?: return@get call.respond(ResponseOk().also {
                it.ok = false
                it.exception = NotFoundException("Id $id não foi encontrado")
            })

            call.respond(Response(retorno))
        }

        post {
            val customer = call.receive<Pokemon>()
            listaPokemonTeste.add(customer)
            call.respond(ResponseOk())
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(ResponseOk().also {
                it.ok = false
                it.exception = BadRequestException("Cabeçalho mal formado")
            })

            if (listaPokemonTeste.removeIf { it.id == id.toInt() }) call.respond(ResponseOk())
            else call.respond(ResponseOk().also {
                it.ok = false
                it.exception = NotFoundException("Id $id não foi encontrado")
            })

        }
    }
}