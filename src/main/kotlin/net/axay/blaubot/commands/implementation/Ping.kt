package net.axay.blaubot.commands.implementation

import com.gitlab.kordlib.kordx.emoji.Emojis
import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.channel.MessageChannelBehavior
import kotlin.random.Random

@KordPreview
class Ping(bot: ExtensibleBot) : Extension(bot) {
    override val name = "ping_command"

    override suspend fun setup() {
        slashCommand {
            name = "ping"
            description = "Play table tennis with the bot"

            action {
                followUp(" ")
                val textChannel = channel
                if (textChannel is MessageChannelBehavior) {
                    if (Random.nextInt(6) == 1) {
                        textChannel.createMessage("Peng!")
                        textChannel.createMessage("${Emojis.fullMoonWithFace}${Emojis.gun}")
                    } else {
                        textChannel.createMessage("${Emojis.pingPong}")
                        textChannel.createMessage("Pong! ${Emojis.grinning}")
                    }
                }
            }
        }
    }
}
