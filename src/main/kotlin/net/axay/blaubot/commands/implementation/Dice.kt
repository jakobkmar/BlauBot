package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.rest.builder.interaction.embed
import dev.kord.x.emoji.Emojis
import kotlinx.coroutines.delay
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Dice : SlashCommand(
    "dice",
    "Rolls a random number for you"
) {
    private val numberEmojis = listOf(
        Emojis.one,
        Emojis.two,
        Emojis.three,
        Emojis.four,
        Emojis.five,
        Emojis.six,
    )

    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        val ack = interaction.acknowledgePublic()

        val loadingImg = interaction.channel.createEmbed {
            image = "https://www.animierte-gifs.net/data/media/710/animiertes-wuerfel-bild-0104.gif"
        }

        delay(2000)

        loadingImg.delete()

        ack.followUp {
            embed {
                title = "You rolled the dice"
                field {
                    name = "Result"
                    value = "${Emojis.flushed} ${Emojis.pointRight} ${numberEmojis.random()}"
                }
            }
        }
    }
}
