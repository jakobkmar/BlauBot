package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview

@KordPreview
class Contribute(bot: ExtensibleBot) : Extension(bot) {
    override val name = "contribute_command"

    override suspend fun setup() {
        slashCommand {
            name = "contribute"
            description = "Shows you how you can contribute to the bot"

            action {
                publicFollowUp {
                    content = "Go to the following GitHub repository to contribute to the bot. A contribution can be a whole new command or feature, but also an issue or feature request (if you cannot code). https://github.com/bluefireoly/BlauBot"
                }
            }
        }
    }
}
