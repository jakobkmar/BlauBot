package net.axay.blaubot.commands.implementation

import com.gitlab.kordlib.kordx.emoji.Emojis
import dev.kord.common.annotation.KordPreview
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand
import kotlin.random.Random

@KordPreview
object Ping : SlashCommand(
    "ping",
    "Play table tennis with the bot"
) {

    override suspend fun handleCommand(interaction: Interaction) {
        interaction.acknowledge(true)
        if (Random.nextInt(6) == 1) {
            interaction.channel.createMessage("Peng!")
            interaction.channel.createMessage("${Emojis.fullMoonWithFace}${Emojis.gun}")
        } else {
            interaction.channel.createMessage("${Emojis.pingPong}")
            interaction.channel.createMessage("Pong! ${Emojis.grinning}")
        }
    }

}
