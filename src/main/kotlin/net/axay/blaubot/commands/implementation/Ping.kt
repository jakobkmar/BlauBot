package net.axay.blaubot.commands.implementation

import com.gitlab.kordlib.kordx.emoji.Emojis
import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview
import kotlin.random.Random

@KordPreview
class Ping(bot: ExtensibleBot) : Extension(bot) {
    override val name = "ping_command"

    override suspend fun setup() {
        slashCommand {
            name = "ping"
            description = "Play table tennis with the bot"

            action {
                val peng = Random.nextInt(6) == 1
                publicFollowUp {
                    content = if (peng) "Peng!" else "${Emojis.pingPong}"
                }
                if (peng)
                    channel.createMessage("${Emojis.fullMoonWithFace}${Emojis.gun}")
                else
                    channel.createMessage("Pong! ${Emojis.grinning}")
            }
        }
    }
}
