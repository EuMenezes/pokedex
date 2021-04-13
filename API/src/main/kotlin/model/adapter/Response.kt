package model.adapter

import kotlinx.serialization.Serializable

@Serializable
class Response<T>() : ResponseOk() {

    constructor(value: T?) : this() {
        this.value = value
    }

    var value: T? = null
}