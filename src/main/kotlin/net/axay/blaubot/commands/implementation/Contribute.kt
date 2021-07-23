package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Contribute : SlashCommand(
    "contribute",
    "Shows you how you can contribute to the bot"
) {
    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        interaction.acknowledgePublic().followUp {
            content = "Go to the following GitHub repository to contribute to the bot. A contribution can be a whole new command or feature, but also an issue or feature request (if you cannot code). https://github.com/bluefireoly/BlauBot"
        }
    }
}
