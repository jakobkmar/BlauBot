package net.axay.blaubot.commands

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.string
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

private val userinfo = mapOf(
    "whis" to "weeb",
    "3Digits" to "PvP Gott.",
    "bokutoc" to "Gute Nacht boku"
)

@KordPreview
object Legenden : SlashCommand(
    "userinfo",
    "Mithilfe dieses Commands kannst du dich über bestimme User informieren",
    {
        subCommand("info", "Gibt den Informationstext zu einer einzelnen Persönlichkeit aus") {
            string("name", "Name der Person") {
                required = true
                for (it in userinfo)
                    choice(it.key, it.key)
            }
        }
    }
) {

    override suspend fun handleCommand(interaction: Interaction) {
        val userinfo = interaction.command.subCommands["info"]?.options?.get("name")?.string()
        if (userinfo != null) {
            val userinfo = userinfo[UserName]
            if (userinfo != null) {
                interaction.acknowledge(true).followUp {
                    embed {
                        field {
                            name = UserName
                            value = userinfo
                        }
                    }
                }
            }
        }
    }

}
