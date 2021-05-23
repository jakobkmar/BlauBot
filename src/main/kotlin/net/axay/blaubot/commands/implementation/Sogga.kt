package net.axay.blaubot.commands.implementation

import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.entity.interaction.InteractionCommand
import net.axay.blaubot.commands.api.SlashCommand

object Sogga : SlashCommand (
    "sogga",
    "Shows sogga to you"
) {
    private val soggaImages = listOf(
        "https://i.kym-cdn.com/entries/icons/original/000/036/896/soggacover.jpg",
        "https://media1.tenor.com/images/4c771b4cd0aaedbedb6e87cd6feaaf3d/tenor.gif",
        "https://static.wikia.nocookie.net/floppapedia-revamped/images/6/6c/Obraz_2021-02-15_135626.webp/revision/latest/top-crop/width/360/height/450?cb=20210224164127"
    )

    override suspend fun execute(interaction: Interaction, command: InteractionCommand) {
        interaction.ackowledgePublic().followUp {
            embed {
                title = "Sogga"
                image = Sogga.soggaImages.random()
                description = "Sogga is an extension meme of Floppa. Both charakters are often used in the same way"
            }
        }
    }


}