package net.axay.blaubot.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import kotlin.reflect.full.isSubclassOf

enum class RequestMethod {
    GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH
}

/**
 * Sends a HTTP request and parses the response as a string with given [charset].
 */
fun httpString(
    url: String,
    method: RequestMethod = RequestMethod.GET,
    charset: Charset = Charsets.UTF_8
) = httpRequest(url, method) { readText(charset) }

/**
 * Sends a HTTP request and parses the result as JSON.
 * [T] may either be [JsonElement] / one of its subclasses or any other class,
 * which is then tried to be deserialized to.
 */
inline fun <reified T> httpJson(
    url: String,
    method: RequestMethod = RequestMethod.GET,
    charset: Charset = Charsets.UTF_8
): T = httpRequest(url, method) {
    if (T::class.isSubclassOf(JsonElement::class)) {
        val element = Json.parseToJsonElement(readText(charset))
        when {
            T::class.isSubclassOf(JsonPrimitive::class) -> element.jsonPrimitive
            T::class == JsonArray::class -> element.jsonArray
            T::class == JsonObject::class -> element.jsonObject
            else -> element
        } as T
    } else {
        Json.decodeFromString(readText())
    }
}

/**
 * Sends a HTTP request and allows handling of the resulting [InputStream],
 * which is closed automatically.
 */
inline fun <T> httpRequest(
    url: String,
    method: RequestMethod = RequestMethod.GET,
    handler: InputStream.() -> T
): T = httpRequest(url, method).use(handler)

/**
 * Sends a HTTP request to [url] with [method] and returns the resulting
 * [InputStream], which has to be closed by the user.
 *
 * Note that currently no advanced configuration or sending of a body is
 * possible, this will be added when necessary.
 */
fun httpRequest(
    url: String,
    method: RequestMethod = RequestMethod.GET,
): InputStream {
    val con = URL(url).openConnection() as HttpURLConnection
    con.requestMethod = method.name
    con.connectTimeout = 10000
    con.readTimeout = 10000
    con.setRequestProperty("User-Agent", "BlauBot")
    con.connect()
    if (con.responseCode in 200..299) {
        return con.inputStream
    } else {
        throw IOException("Request to $url failed with status ${con.responseCode} ${con.responseMessage}")
    }
}

fun InputStream.readText(charset: Charset = Charsets.UTF_8) =
    readBytes().toString(charset)
