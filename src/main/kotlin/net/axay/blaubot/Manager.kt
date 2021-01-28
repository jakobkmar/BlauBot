package net.axay.blaubot

import dev.kord.common.annotation.KordPreview
import dev.kord.core.Kord
import net.axay.blaubot.commands.api.CommandManager
import net.axay.blaubot.config.ConfigManager

@KordPreview
suspend fun main() = Manager.start()

object Manager {

    lateinit var client: Kord; private set

    @KordPreview
    suspend fun start() {

        client = Kord(ConfigManager.discordApplication.token
            ?: error("Configure the application before running it"))

        CommandManager.init()

        client.login()

    }

}
