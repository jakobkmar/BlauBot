package net.axay.blaubot.utils

import com.google.gson.JsonElement

val JsonElement.asStringOrNull get() = try {
    asString
} catch (exc: Exception) {
    null
}

val JsonElement.asJsonObjectOrNull get() = try {
    asJsonObject
} catch (exc: Exception) {
    null
}
