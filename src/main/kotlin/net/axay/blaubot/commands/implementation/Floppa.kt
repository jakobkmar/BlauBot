package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Floppa : SlashCommand(
    "floppa",
    "Get Floppa"
) {
    override suspend fun handleCommand(interaction: Interaction) {
        interaction.acknowledge(true).followUp {
            embed {
                title = "Floppa"
                image = "https://media.tenor.com/images/40401b6421797ab623387168d8ecc88d/tenor.gif"
                description = "This is floppa. Some people even say that he is better than bingus."
            }
        }
    }
}
