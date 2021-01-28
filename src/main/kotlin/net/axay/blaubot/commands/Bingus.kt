package net.axay.blaubot.commands

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Bingus: SlashCommand(
    "bingus",
    "get bingus"
) {
    override suspend fun handleCommand(interaction: Interaction) {
        interaction.acknowledge(true).followUp {
            embed {
                title = "Bingus"
                image = "https://media.tenor.co/videos/e37c4423a3db034d561e1a7c0fc2e4f9/mp4"
                description = "An internet meme of a Sphynx cat with multiple filters to make the cat appear smooth, this cat has come to be known as ‘Bingus’ due to popularity"
            }
        }
    }
}