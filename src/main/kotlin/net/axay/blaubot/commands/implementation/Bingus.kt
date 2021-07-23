package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.rest.builder.interaction.embed
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Bingus : SlashCommand(
    "bingus",
    "Shows bingus to you"
) {
    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        interaction.acknowledgePublic().followUp {
            embed {
                title = "Bingus"
                image = "https://i.kym-cdn.com/photos/images/newsfeed/001/920/524/12f.jpg"
                description = "An internet meme of a Sphynx cat, this cat has come to be known as ‘Bingus’"
            }
        }
    }
}
