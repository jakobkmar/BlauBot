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
                image = "https://i.kym-cdn.com/photos/images/newsfeed/001/920/524/12f.jpg"
                description = "An internet meme of a Sphynx cat, this cat has come to be known as ‘Bingus’"
            }
        }
    }
}
