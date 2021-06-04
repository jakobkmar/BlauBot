package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.core.entity.interaction.string
import dev.kord.x.emoji.Emojis
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object UHC : SlashCommand(
    "uhc",
    "Creates an automatic UHC Alert",
    {
        string("host", "Host des UHC's")
        string("time", "Start-zeit")
        string("hostmedia", "Media link zum stream des hosts")
        string("teamsize", "Teamgröße")
    }
) {
    override suspend fun execute(interaction: Interaction, command: InteractionCommand) {
        val isAdmin = interaction.user.asMemberOrNull(interaction.data.guildId.value ?: return)
            ?.getPermissions()?.contains(Permission.Administrator) == true

        if (isAdmin) {
            val host = command.options["host"]?.string().orEmpty()
            val time = command.options["time"]?.string().orEmpty()
            val hostmedia = command.options["hostmedia"]?.string().orEmpty()
            val teamsize = command.options["teamsize"]?.string().orEmpty()

            val response = interaction.ackowledgePublic()
            response.followUp {
                content = "**UHC** \n" +
                        "\n" +
                        "Server: `uhc.hglabor.de` \n" +
                        "Version: `1.13 - 1.16` \n" +
                        "\n" +
                        "host: `$host` ($hostmedia) \n" +
                        "Start: `$time` Uhr \n" +
                        "Team-Größe: `$teamsize` \n" +
                        "\n" +
                        "GL Have fun" + Emojis.fourLeafClover  + "\n" +
                        "@here"
            }
        } else {
            interaction.acknowledgeEphemeral().followUp("Insufficient permissions")
        }
    }
}
