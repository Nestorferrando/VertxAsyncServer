package com.nospoon.vertxserver.messages

import com.google.gson.GsonBuilder

/**
 * Created by Nestor on 2/24/2017.
 */


class ContainerMessage(val fullyQualifiedMessageName: String, val serializedMessage: String)


private val gson = GsonBuilder().create()

fun deserialize(serializedData: String): List<Any> {
    val data = java.util.ArrayList<Any>()
    splitBufferIntoJSONS(serializedData).forEach { serializedMsg ->
        try {
            val container = gson.fromJson(serializedMsg, ContainerMessage::class.java)
            val c = Class.forName(container.fullyQualifiedMessageName)
            data.add(gson.fromJson<Any>(container.serializedMessage, c))
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }
    return data
}

fun serialize(msg: Any): String {
    val name = msg::class.java.canonicalName
    return gson.toJson(ContainerMessage(name, gson.toJson(msg)))
}

private fun splitBufferIntoJSONS(buffer: String): List<String> {
    val splitted = java.util.ArrayList<String>()
    var bufferTemp = buffer
    var tokenIndex = bufferTemp.indexOf("}{")
    while (tokenIndex > -1) {
        splitted.add(bufferTemp.substring(0, tokenIndex + 1))
        bufferTemp = bufferTemp.substring(tokenIndex + 1, bufferTemp.length)
        tokenIndex = bufferTemp.indexOf("}{")
    }
    splitted.add(bufferTemp)
    return splitted
}