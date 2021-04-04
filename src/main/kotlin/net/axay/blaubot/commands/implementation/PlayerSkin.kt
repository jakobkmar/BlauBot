package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.commands.converters.string
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview

@KordPreview
class PlayerSkin(bot: ExtensibleBot) : Extension(bot) {
    override val name = "playerskin_command"

    private class Args : Arguments() {
        val playername by string("playername", "The name of the player")
    }

    override suspend fun setup() {
        slashCommand(::Args) {
            name = "playerskin"
            description = "Shows you the skin of the given player"

            action {
                followUp {
                    embed {
                        val playerName = arguments.playername
                        title = playerName
                        description = "This is the skin of $playerName"
                        image = "https://minecraftskinstealer.com/api/v1/skin/render/fullbody/$playerName/700"
                    }
                }
            }
        }
    }
}
