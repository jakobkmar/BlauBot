package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview

@KordPreview
class Bingus(bot: ExtensibleBot) : Extension(bot) {
    override val name = "bingus_command"

    override suspend fun setup() {
        slashCommand {
            name = "bingus"
            description = "Shows bingus to you"

            action {
                publicFollowUp {
                    embed {
                        title = "Bingus"
                        image = "https://i.kym-cdn.com/photos/images/newsfeed/001/920/524/12f.jpg"
                        description = "An internet meme of a Sphynx cat, this cat has come to be known as ‘Bingus’"
                    }
                }
            }
        }
    }
}
