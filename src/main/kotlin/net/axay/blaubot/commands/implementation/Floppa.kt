package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview

private val floppaImages = listOf(
    "https://media.tenor.com/images/40401b6421797ab623387168d8ecc88d/tenor.gif",
    "https://media.tenor.com/images/8f4b75ce9292825efa37a66ba85f3335/tenor.gif",
    "https://media.tenor.com/images/09f2bba19bd9615695b856c0f1235a36/tenor.gif",
)

@KordPreview
class Floppa(bot: ExtensibleBot) : Extension(bot) {
    override val name = "floppa_command"

    override suspend fun setup() {
        slashCommand {
            name = "floppa"
            description = "Shows floppa to you"

            action {
                publicFollowUp {
                    embed {
                        title = "Floppa"
                        image = floppaImages.random()
                        description = "This is floppa. Some people even say that he is better than bingus."
                    }
                }
            }
        }
    }
}
