package util

import io.ktor.routing.*
import model.adapter.ResponseException
import model.adapter.ResponseOk
import kotlin.reflect.full.primaryConstructor

fun Route.ResponseError(error: Throwable) = ResponseException(this.toString(), error)

inline fun <reified T : ResponseOk> tryCatch(rota: Route, sucess: () -> T): T {
    return try {
        sucess()
    } catch (t: Throwable) {
        T::class.primaryConstructor!!.call().also {
            it.ok = false
            it.exception = rota.ResponseError(t)
        }
    }
}