package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.entity.interaction.InteractionCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import net.axay.blaubot.commands.api.SlashCommand
import net.axay.blaubot.utils.httpJson

@KordPreview
object Fox : SlashCommand(
    "fox",
    "Shows a cool fox for your enjoyment"
) {
    override suspend fun execute(interaction: Interaction, command: InteractionCommand) {
        interaction.ackowledgePublic().followUp {
            content = withContext(Dispatchers.IO) {
                httpJson<RandomFox>("https://randomfox.ca/floof/").image
            }
        }
    }

    @Serializable
    private data class RandomFox(val image: String, val link: String)
}
