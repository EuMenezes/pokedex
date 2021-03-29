package model.adapter

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(var value: T?) : ResponseOk()

@Serializable
data class ResponseList<T>(var value: List<T>? = null) : ResponseOk()

@Serializable
open class ResponseOk {
    var ok: Boolean = true

    //Não funciona
    @Contextual
    var exception: Exception? = null 
}