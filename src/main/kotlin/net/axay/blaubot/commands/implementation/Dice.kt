package net.axay.blaubot.commands.implementation

import com.gitlab.kordlib.kordx.emoji.Emojis
import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.entity.interaction.Interaction
import kotlinx.coroutines.delay
import net.axay.blaubot.commands.api.SlashCommand

private val numberEmojis = listOf(
    Emojis.one,
    Emojis.two,
    Emojis.three,
    Emojis.four,
    Emojis.five,
    Emojis.six,
)

@KordPreview
object Dice : SlashCommand(
    "dice",
    "Rolls a random number for you"
) {

    override suspend fun handleCommand(interaction: Interaction) {

        interaction.acknowledge(true)

        val loadingImg = interaction.channel.createEmbed {
            image = "https://www.animierte-gifs.net/data/media/710/animiertes-wuerfel-bild-0104.gif"
        }

        delay(2000)

        loadingImg.delete()

        interaction.channel.createEmbed {
            title = "You rolled the dice"
            field {
                name = "Result"
                value = "${Emojis.flushed} ${Emojis.pointRight} ${numberEmojis.random()}"
            }
        }

    }

}
