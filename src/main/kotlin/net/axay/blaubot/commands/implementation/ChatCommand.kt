package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.commands.converters.string
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.utils.hasPermission
import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.Permission
import dev.kord.core.behavior.channel.MessageChannelBehavior

@KordPreview
class ChatCommand(bot: ExtensibleBot) : Extension(bot) {
    override val name = "chat_command"

    private class Args : Arguments() {
        val message by string("message", "The message which should be send")
    }

    override suspend fun setup() {
        slashCommand(::Args) {
            name = "chat"
            description = "Allows you to chat via the bot"

            check {
                it.interaction.user.asMemberOrNull(it.interaction.data.guildId.value ?: return@check false)
                    ?.hasPermission(Permission.Administrator) == true
            }

            action {
                // temporarily, this is needed to remove the response design
                publicFollowUp {
                    content = arguments.message
                }.delete()

                channel.createMessage(arguments.message)
            }
        }
    }
}
