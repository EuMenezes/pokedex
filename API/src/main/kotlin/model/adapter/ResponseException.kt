package model.adapter

import kotlinx.serialization.Serializable

@Serializable
class ResponseException {

    constructor()
    constructor(rota: String, error: Throwable) : this() {
        this.route = rota
        this.type = error.javaClass.canonicalName
        this.message = error.message
    }

    var route: String? = null
    var type: String? = null
    var message: String? = null
}