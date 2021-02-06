package net.axay.blaubot.commands

import dev.kord.common.annotation.KordPreview
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Contribute : SlashCommand(
    "contribute",
    "Gives you the link to the GitHub repository of the bot"
) {
    override suspend fun handleCommand(interaction: Interaction) {
        interaction.acknowledge(true)
        interaction.channel.createMessage("Go to the following GitHub repository to contribute to the bot. A contribution can be a whole new command or feature, but also an issue or feature request (if you cannot code). https://github.com/bluefireoly/BlauBot!")
    }
}
