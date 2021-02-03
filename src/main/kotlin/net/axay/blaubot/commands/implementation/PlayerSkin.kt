package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.string
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object PlayerSkin : SlashCommand(
    "playerskin",
    "Get the skin of the given player",
    {
        string("name", "Name of the player") {
            required = true
        }
    }
) {
    override suspend fun handleCommand(interaction: Interaction) {
        val playerName = interaction.command.options["name"]?.string()
        if (playerName != null) {
            interaction.acknowledge(true).followUp {
                embed {
                    title = playerName
                    description = "This is the skin of $playerName"
                    image = "https://minecraftskinstealer.com/api/v1/skin/render/fullbody/$playerName/700"
                }
            }
        }
    }
}
