package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.rest.builder.interaction.embed
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Floppa : SlashCommand(
    "floppa",
    "Shows floppa to you"
) {
    private val floppaImages = listOf(
        "https://media.tenor.com/images/40401b6421797ab623387168d8ecc88d/tenor.gif",
        "https://media.tenor.com/images/8f4b75ce9292825efa37a66ba85f3335/tenor.gif",
        "https://media.tenor.com/images/09f2bba19bd9615695b856c0f1235a36/tenor.gif",
    )

    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        interaction.acknowledgePublic().followUp {
            embed {
                title = "Floppa"
                image = floppaImages.random()
                description = "This is floppa. Some people even say that he is better than bingus."
            }
        }
    }
}
