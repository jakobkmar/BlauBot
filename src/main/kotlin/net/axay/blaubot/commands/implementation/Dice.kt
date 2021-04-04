package net.axay.blaubot.commands.implementation

import com.gitlab.kordlib.kordx.emoji.Emojis
import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.channel.MessageChannelBehavior
import dev.kord.core.behavior.channel.createEmbed
import kotlinx.coroutines.delay

private val numberEmojis = listOf(
    Emojis.one,
    Emojis.two,
    Emojis.three,
    Emojis.four,
    Emojis.five,
    Emojis.six,
)

@KordPreview
class Dice(bot: ExtensibleBot) : Extension(bot) {
    override val name = "dice_command"

    override suspend fun setup() {
        slashCommand {
            name = "dice"
            description = "Rolls a random number for you"

            action {
                (channel as? MessageChannelBehavior)?.let {
                    val loadingImg = it.createEmbed {
                        image = "https://www.animierte-gifs.net/data/media/710/animiertes-wuerfel-bild-0104.gif"
                    }

                    delay(2000)

                    loadingImg.delete()

                    it.createEmbed {
                        title = "You rolled the dice"
                        field {
                            name = "Result"
                            value = "${Emojis.flushed} ${Emojis.pointRight} ${numberEmojis.random()}"
                        }
                    }
                }
            }
        }
    }
}
