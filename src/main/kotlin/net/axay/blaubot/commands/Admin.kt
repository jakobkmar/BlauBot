package net.axay.blaubot.commands

import dev.kord.common.Color
import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.common.entity.string
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object Admin : SlashCommand(
    "admin",
    "Dieser Command bietet Admin Funktionen an. Nicht für Normies gedacht.",
    {
        subCommand("chat", "classified") {
            string("message", "classified")
        }
        subCommand("embed", "classified") {
            string("title", "classified")
            string("message", "classified")
        }
    }
) {

    override suspend fun handleCommand(interaction: Interaction) {

        if (interaction.member.asMemberOrNull()?.getPermissions()?.contains(Permission.Administrator) != true) return

        interaction.acknowledge(false)
        interaction.command.subCommands["chat"]?.options?.get("message")?.string()?.let {
            interaction.channel.createMessage(it)
        }
        interaction.command.subCommands["embed"]?.options?.get("message")?.string()?.let {
            interaction.acknowledge(true).followUp {
                embed {
                    title = interaction.command.subCommands["embed"]?.options?.get("title")?.string()
                    image = interaction.member.asUser().avatar.url
                    description = it
                }
            }
        }

    }

}
