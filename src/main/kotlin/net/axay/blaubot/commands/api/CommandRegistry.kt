package net.axay.blaubot.commands.api

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.createApplicationCommand
import dev.kord.core.behavior.createApplicationCommands
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@KordPreview
object CommandRegistry {
    val registeredCommands = HashMap<String, SlashCommand>()

    suspend fun applyToBot(kord: Kord) = coroutineScope {
        for ((_, slashCommand) in registeredCommands) {
            if (!slashCommand.test)
                kord.createGlobalApplicationCommand(slashCommand.name, slashCommand.description, slashCommand.builder)
        }
        launch {
            kord.globalCommands.collect {
                if (!registeredCommands.containsKey(it.name))
                    it.delete()
            }
        }
        println("Set up global commands")

        val testguild = kord.getGuild(Snowflake(738122976280707125))
        if (testguild != null) {
            for ((_, slashCommand) in registeredCommands) {
                if (slashCommand.test)
                    testguild.createApplicationCommand(slashCommand.name, slashCommand.description, slashCommand.builder)
            }
            launch {
                testguild.commands.collect {
                    if (!registeredCommands.containsKey(it.name))
                        it.delete()
                }
            }
            println("Set up test guild")
        }

        kord.on<InteractionCreateEvent> {
            registeredCommands[interaction.command.rootName]?.execute(interaction, interaction.command)
        }
    }
}
