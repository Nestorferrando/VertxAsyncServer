package com.nospoon.vertxserver.core.model

import java.util.HashMap

/**
 * Created by Nestor on 7/26/2016.
 */
class Player(var session: String) {
   val data: Map<String, String> = HashMap()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.let { it::class.java } != this::class.java) return false

        other as Player

        if (session != other.session) return false

        return true
    }

    override fun hashCode(): Int {
        return session.hashCode()
    }


}
