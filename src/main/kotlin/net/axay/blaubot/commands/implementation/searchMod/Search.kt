package net.axay.blaubot.commands.implementation.searchMod

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.core.entity.interaction.string
import dev.kord.rest.builder.interaction.PublicInteractionResponseCreateBuilder
import dev.kord.rest.builder.interaction.embed
import net.axay.blaubot.commands.api.SlashCommand
import net.axay.pacmc.app.data.Repository
import net.axay.pacmc.app.repoapi.RepositoryApi
import net.axay.pacmc.app.repoapi.model.CommonProjectInfo

private sealed interface SearchResponse

private data class SearchResponseSuccess(
    var results: List<CommonProjectInfo>,
) : SearchResponse

private data class SearchResponseFailure(
    val reason: String,
    val fetchedTerm: String,
) : SearchResponse


@KordPreview
object Search : SlashCommand(
    "search",
    "search a mod on modrinth",
    {
        string("name", "modname") { required = true }
    }
) {
    private const val maxResults: Int = 5

    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        val fetchTerm = command.options["name"]!!.string()
        var searchResponse: SearchResponse = SearchResponseSuccess(emptyList())

        val result = kotlin.runCatching {
            RepositoryApi.search(fetchTerm, Repository.MODRINTH)
        }
        result.onFailure { exc ->
            println("Failed to fetch search results for '${fetchTerm}' (${exc.message})")
            searchResponse =
                SearchResponseFailure((exc.message ?: "unknown reason") + " (${exc::class.simpleName})", fetchTerm)
        }.onSuccess {
            searchResponse = SearchResponseSuccess(it.filterNot { it.author == "Cynosphere" })
        }

        if (searchResponse is SearchResponseFailure) {
            interaction.respondEphemeral {
                content = "Failed to fetch search results for '${fetchTerm}'"
            }
        } else {
            if ((searchResponse as SearchResponseSuccess).results.isEmpty()) {
                interaction.respondPublic {
                    content = "cant find mods for $fetchTerm"
                }
                return
            }

            interaction.respondPublic {
                content = "results for $fetchTerm"

                repeat(Integer.min(maxResults, (searchResponse as SearchResponseSuccess).results.size)) {
                    projectInfoEmbed((searchResponse as SearchResponseSuccess).results[it])
                }
            }
        }
    }

    private fun PublicInteractionResponseCreateBuilder.projectInfoEmbed(project: CommonProjectInfo) = embed {
        title = "**${project.name}** by **${project.author}**"
        description = project.description

        thumbnail {
            url = project.iconUrl?.ifEmpty { null } ?: "https://cdn.modrinth.com/placeholder.svg"
        }

        field {
            name = "latest version"
            value = project.latestVersion.toString()
        }
    }
}
