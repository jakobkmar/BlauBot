package net.axay.blaubot

import com.kotlindiscord.kord.extensions.ExtensibleBot
import dev.kord.common.annotation.KordPreview
import net.axay.blaubot.commands.implementation.*
import net.axay.blaubot.config.ConfigManager

@KordPreview
suspend fun main() = Manager.start()

object Manager {
    lateinit var bot: ExtensibleBot; private set

    @KordPreview
    suspend fun start() {
        val token = ConfigManager.discordApplication.token
            ?: error("Configure the application before running it")

        bot = ExtensibleBot(token) {
            slashCommands {
                enabled = true
            }
            extensions {
                add(::AnimeSearch)
                add(::AnimeTerms)
                add(::Bingus)
                add(::ChatCommand)
                add(::Contribute)
                add(::Dice)
                add(::Floppa)
                add(::Fox)
                add(::Legends)
                add(::Ping)
                add(::PlayerSkin)
                add(::RandomAnime)
            }
        }

        bot.start()
    }
}
