package model.adapter

import kotlinx.serialization.Serializable

@Serializable
open class ResponseOk {
    constructor()

    var ok: Boolean = true
    var exception: ResponseException? = null
}