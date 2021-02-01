package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.string
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand

private val legenden = mapOf(
    "Ryan Harter" to "Er ist eine Ktor Legende, Tech Talk Veteran und Pixite Developer.",
    "Sebastian Aigner" to "Er macht die besten Screencasts und ist einfach ein Talent, egal um welche Kotlin Library es sich handelt.",
    "Kevin Chromik" to "Auf seinem Kanal geht alles rund um Softwareentwicklung, Studium und Karriere im Informatikbereich."
)

@KordPreview
object Legenden : SlashCommand(
    "legenden",
    "Mithilfe dieses Commands kannst du dich über legendäre Personen informieren",
    {
        subCommand("info", "Gibt den Informationstext zu einer einzelnen Persönlichkeit aus") {
            string("name", "Name der Person") {
                required = true
                for (it in legenden)
                    choice(it.key, it.key)
            }
        }
    }
) {

    override suspend fun handleCommand(interaction: Interaction) {
        val legendenName = interaction.command.subCommands["info"]?.options?.get("name")?.string()
        if (legendenName != null) {
            val legendenInfo = legenden[legendenName]
            if (legendenInfo != null) {
                interaction.acknowledge(true).followUp {
                    embed {
                        field {
                            name = legendenName
                            value = legendenInfo
                        }
                    }
                }
            }
        }
    }

}
