package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.x.emoji.Emojis
import net.axay.blaubot.commands.api.SlashCommand
import kotlin.random.Random

@KordPreview
object Ping : SlashCommand(
    "ping",
    "Play table tennis with the bot"
) {
    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        val peng = Random.nextInt(6) == 1
        interaction.acknowledgePublic().followUp {
            content = if (peng) "Peng!" else "${Emojis.pingPong}"
        }
        if (peng)
            interaction.channel.createMessage("${Emojis.fullMoonWithFace}${Emojis.gun}")
        else
            interaction.channel.createMessage("Pong! ${Emojis.grinning}")
    }
}
