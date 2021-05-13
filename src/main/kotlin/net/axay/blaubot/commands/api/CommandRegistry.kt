package net.axay.blaubot.commands.api

import dev.kord.common.annotation.KordPreview
import dev.kord.core.Kord
import dev.kord.core.behavior.createApplicationCommand
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@KordPreview
object CommandRegistry {
    val registeredCommands = HashMap<String, SlashCommand>()

    suspend fun applyToBot(kord: Kord) = coroutineScope {
        kord.guilds.map { guild ->
            launch {
                guild.commands.map {
                    launch {
                        it.delete()
                    }
                }.collect { it.join() }

                registeredCommands.map { (_, slashCommand) ->
                    launch {
                        guild.createApplicationCommand(slashCommand.name, slashCommand.description, slashCommand.builder)
                    }
                }.forEach { it.join() }
                println("Finished editing guild ${guild.name}")
            }
        }.collect { it.join() }

        kord.on<InteractionCreateEvent> {
            registeredCommands[interaction.command.rootName]?.execute(interaction, interaction.command)
        }
    }
}
