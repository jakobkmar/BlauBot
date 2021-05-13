package net.axay.blaubot

import dev.kord.common.annotation.KordPreview
import dev.kord.core.Kord
import net.axay.blaubot.commands.api.CommandRegistry
import net.axay.blaubot.commands.implementation.*
import net.axay.blaubot.config.ConfigManager

@KordPreview
suspend fun main() {
    val token = ConfigManager.discordApplication.token
        ?: error("Configure the application before running it")

    val bot = Kord(token)

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

    CommandRegistry.applyToBot(bot)

    bot.login()
}
