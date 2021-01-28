package net.axay.blaubot.commands

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Floppa : SlashCommand(
    "floppa",
    "get Floppa"
) {
    override suspend fun handleCommand(interaction: Interaction) {
        interaction.acknowledge(true).followUp {
            embed {
                title = "Floppa"
                image = "https://media.tenor.com/images/40401b6421797ab623387168d8ecc88d/tenor.gif"
                description = "This is floppa and he it's better than bingus"
            }
        }
    }
}