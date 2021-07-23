package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.behavior.interaction.followUpEphemeral
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.core.entity.interaction.string
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object ChatCommand : SlashCommand(
    "chat",
    "Allows you to chat via the bot",
    {
        string("message", "The message which should be send")
    }
) {
    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        val isAdmin = interaction.user.asMemberOrNull(interaction.data.guildId.value ?: return)
            ?.getPermissions()?.contains(Permission.Administrator) == true

        if (isAdmin) {
            val message = command.options["message"]?.string().orEmpty()

            val response = interaction.acknowledgePublic()
            response.followUp {
                content = message
            }.delete()

            interaction.channel.createMessage(message)
        } else {
            interaction.acknowledgeEphemeral().followUpEphemeral { content = "Insufficient permissions" }
        }
    }
}
