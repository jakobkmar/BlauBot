package net.axay.blaubot

import dev.kord.common.annotation.KordPreview
import dev.kord.core.Kord
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import net.axay.blaubot.commands.api.CommandRegistry
import net.axay.blaubot.commands.implementation.*
import net.axay.blaubot.config.ConfigManager

val ktorClient = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Values.jsonInstance)
    }
}

@KordPreview
suspend fun main() {
    val token = ConfigManager.discordApplication.token
        ?: error("Configure the application before running it")

    val bot = Kord(token)

    Sogga
    AnimeSearch
    Bingus
    ChatCommand
    Contribute
    Dice
    Floppa
    Fox
    Ping
    PlayerSkin
    RandomAnime
    GithubProfile

    CommandRegistry.applyToBot(bot)

    println("Logging in...")

    bot.login()
}
