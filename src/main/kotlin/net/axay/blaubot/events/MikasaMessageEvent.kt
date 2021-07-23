package net.axay.blaubot.events

import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.x.emoji.Emojis
import dev.kord.x.emoji.addReaction

object MikasaMessageEvent {
    fun enable(bot: Kord) {
        bot.on<MessageCreateEvent> {
            if (this.member?.id == Snowflake("680863987080101908")) {
                this.message.addReaction(
                    Emojis.joy
                )
            }
        }
    }
}