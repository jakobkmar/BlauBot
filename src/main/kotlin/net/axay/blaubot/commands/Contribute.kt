package net.axay.blaubot.commands

import dev.kord.common.annotation.KordPreview
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Contribute : SlashCommand(
    "contribute",
    "Shows how to contribute to the bot"
) {
    override suspend fun handleCommand(interaction: Interaction) {
        interaction.acknowledge(true)
        interaction.channel.createMessage("Hier kannst du dich am Bot beteiligen: https://github.com/bluefireoly/BlauBot!")
    }
}