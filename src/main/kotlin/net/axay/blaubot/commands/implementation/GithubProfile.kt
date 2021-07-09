package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.Interaction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.core.entity.interaction.string
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object GithubProfile : SlashCommand(
    "githubprofile",
    "Shows you the profile of a given user",
    {
        string("user", "The name of the user")
    }
) {
    override suspend fun execute(interaction: Interaction, command: InteractionCommand) {
        interaction.ackowledgePublic().followUp {
            embed {
                val user = command.options["user"]?.string().orEmpty()
                title = user
                description = "This is the profile of $user"
                image = "https://api.vollkorn.me/githubUser/?user=$user"
            }
        }
    }
}
