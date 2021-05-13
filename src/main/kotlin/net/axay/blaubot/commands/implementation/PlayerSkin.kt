package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.core.entity.interaction.string
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object PlayerSkin : SlashCommand(
    "playerskin",
    "Shows you the skin of the given player",
    {
        string("playername", "The name of the player")
    }
) {
    override suspend fun execute(interaction: Interaction, command: InteractionCommand) {
        interaction.ackowledgePublic().followUp {
            embed {
                val playerName = command.options["playername"]?.string().orEmpty()
                title = playerName
                description = "This is the skin of $playerName"
                image = "https://minecraftskinstealer.com/api/v1/skin/render/fullbody/$playerName/700"
            }
        }
    }
}
