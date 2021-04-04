package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.commands.converters.string
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview
import net.axay.blaubot.commands.api.testGuild

private val legends = mapOf(
    "Ryan Harter" to "Er ist eine Ktor Legende, Tech Talk Veteran und Pixite Developer.",
    "Sebastian Aigner" to "Er macht die besten Screencasts und ist einfach ein Talent, egal um welche Kotlin Library es sich handelt.",
    "Kevin Chromik" to "Auf seinem Kanal geht alles rund um Softwareentwicklung, Studium und Karriere im Informatikbereich."
)

@KordPreview
class Legends(bot: ExtensibleBot) : Extension(bot) {
    override val name = "legenden_command"

    private class Args : Arguments() {
        val name by string("name", "Name of the legend")
    }

    override suspend fun setup() {
        slashCommand(::Args) {
            testGuild()

            name = "legendinfo"
            description = "This command informs you about some selected legends"

            action {
                followUp {
                    embed {
                        field {
                            name = arguments.name
                            value = legends[arguments.name].toString()
                        }
                    }
                }
            }
        }
    }
}
