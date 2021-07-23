package net.axay.blaubot.commands.api

import dev.kord.common.annotation.KordPreview
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.rest.builder.interaction.ApplicationCommandCreateBuilder

@KordPreview
abstract class SlashCommand(
    val name: String,
    val description: String,
    val builder: ApplicationCommandCreateBuilder.() -> Unit = { },
    val test: Boolean = false,
) {
    init {
        @Suppress("LeakingThis")
        CommandRegistry.registeredCommands[name] = this
    }

    abstract suspend fun execute(interaction: CommandInteraction, command: InteractionCommand)
}
