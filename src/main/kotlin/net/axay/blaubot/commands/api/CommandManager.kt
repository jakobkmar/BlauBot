package net.axay.blaubot.commands.api

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.createApplicationCommand
import dev.kord.core.entity.Guild
import dev.kord.core.event.guild.GuildCreateEvent
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.flow.collect
import net.axay.blaubot.Manager
import net.axay.blaubot.commands.Contribute
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
        Contribute

        cleanupGuilds()
        registerOnGuilds()

        // add commands to new guilds
        Manager.client.on<GuildCreateEvent> {
            this.guild.cleanupCommands()
            this.guild.registerCommands()
        }

        // handle interactions
        Manager.client.on<InteractionCreateEvent> {
            commands[interaction.command.name]?.handleCommand(interaction)
        }
    }

    /**
     * Register all known commands on the guilds.
     */
    private suspend fun registerOnGuilds() = Manager.client.guilds.collect { it.registerCommands() }

    /**
     * Remove unknown commands from guilds.
     */
    private suspend fun cleanupGuilds() = Manager.client.guilds.collect { it.cleanupCommands() }

    private suspend fun Guild.registerCommands() {
        CommandManager.commands.forEach { commandEntry ->
            val command = commandEntry.value
            createApplicationCommand(command.name, command.description) { command.builder.invoke(this) }
        }
    }

    private suspend fun Guild.cleanupCommands() {
        commands.collect { command ->
            if (!CommandManager.commands.containsKey(command.name))
                command.delete()
        }
    }

}
