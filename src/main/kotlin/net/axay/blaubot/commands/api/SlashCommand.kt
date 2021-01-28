package net.axay.blaubot.commands.api

import dev.kord.common.annotation.KordPreview
import dev.kord.core.entity.interaction.Interaction
import dev.kord.rest.builder.interaction.ApplicationCommandCreateBuilder

@KordPreview
abstract class SlashCommand(
    val name: String,
    val description: String,
    val builder: ApplicationCommandCreateBuilder.() -> Unit = {}
) {

    init {
        @Suppress("LeakingThis")
        CommandManager.register(this)
    }

    abstract suspend fun handleCommand(interaction: Interaction)

}
