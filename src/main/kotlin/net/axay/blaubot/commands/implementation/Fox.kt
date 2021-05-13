package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import net.axay.blaubot.utils.httpJson

@KordPreview
class Fox(bot: ExtensibleBot) : Extension(bot) {
    override val name = "fox_command"

    override suspend fun setup() {
        slashCommand {
            name = "fox"
            description = "Shows a cool fox for your enjoyment"

            action {
                publicFollowUp {
                    content = withContext(Dispatchers.IO) {
                        httpJson<RandomFox>("https://randomfox.ca/floof/").image
                    }
                }
            }
        }
    }

    @Serializable
    private data class RandomFox(val image: String, val link: String)
}
