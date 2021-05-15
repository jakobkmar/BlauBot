package net.axay.blaubot

import kotlinx.serialization.json.Json

object Values {
    val jsonInstance = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
        allowSpecialFloatingPointValues = true
    }
}
