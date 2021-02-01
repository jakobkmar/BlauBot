package net.axay.blaubot.commands.api

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.createApplicationCommand
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.flow.collect
import net.axay.blaubot.Manager
import net.axay.blaubot.commands.implementation.*
import net.axay.blaubot.commands.implementation.anime.AnimeSearch
import net.axay.blaubot.commands.implementation.anime.AnimeTerms
import net.axay.blaubot.commands.implementation.anime.RandomAnime

@KordPreview
object CommandManager {

    private val commands = HashMap<String, SlashCommand>()

    fun register(command: SlashCommand) {
        commands[command.name] = command
    }

    /**
     * Automatically register commands and clean up guilds from old commands.
     */
    suspend fun init() {
        // register commands by mentioning them
        Legenden
        Admin
        Ping
        AnimeTerms
        RandomAnime
        AnimeSearch
        Bingus
        Floppa
        Fox
        Dice
        PlayerSkin

        cleanupGuilds()
        registerOnGuilds()

        Manager.client.on<InteractionCreateEvent> {
            commands[interaction.command.name]?.handleCommand(interaction)
        }
    }

    /**
     * Register all known commands on the guilds.
     */
    private suspend fun registerOnGuilds() {
        Manager.client.guilds.collect {
            commands.forEach { commandEntry ->
                val command = commandEntry.value
                it.createApplicationCommand(command.name, command.description) { command.builder.invoke(this) }
            }
        }
    }

    /**
     * Remove unknown commands from guilds.
     */
    private suspend fun cleanupGuilds() {
        Manager.client.guilds.collect { it.commands.collect { command ->
            if (!commands.containsKey(command.name))
                command.delete()
        } }
    }

}
