package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.core.entity.interaction.string
import dev.kord.rest.builder.interaction.embed
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import net.axay.blaubot.commands.api.SlashCommand
import net.axay.blaubot.ktorClient
import org.apache.commons.lang3.StringUtils

@KordPreview
object AnimeSearch : SlashCommand(
    "animesearch",
    "Get information about any anime",
    {
        string("searchterm", "The search input")
    },
    test = true
) {
    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        val searchterm = command.options["searchterm"]?.string().orEmpty()

        interaction.acknowledgePublic().followUp {
            val animeSearchResult = withContext(Dispatchers.IO) {
                ktorClient.get<Array<AnimeSearchResult>>("https://kitsu.io/api/edge/anime?filter[text]=$searchterm")
            }.firstOrNull()

            if (animeSearchResult != null) {
                embed {
                    title = "Anime Search"

                    image = animeSearchResult.attributes?.coverImage?.original

                    field {
                        name = "Search term"
                        value = searchterm
                    }
                    field {
                        name = "Name"
                        value = """
                                English: ${animeSearchResult.attributes?.titles?.en ?: NOT_AVAILABLE}
                                Japanese: ${animeSearchResult.attributes?.titles?.ja_jp ?: NOT_AVAILABLE}
                            """.trimIndent()
                    }
                    field {
                        name = "Description"
                        value = StringUtils.abbreviate(
                            animeSearchResult.attributes?.description ?: NOT_AVAILABLE, 1024
                        )
                    }
                    field {
                        name = "Rating"
                        value = "${animeSearchResult.attributes?.averageRating ?: NOT_AVAILABLE}%"
                    }
                    field {
                        name = "Age Rating"
                        value = animeSearchResult.attributes?.ageRatingGuide ?: NOT_AVAILABLE
                    }
                    field {
                        name = "Status"
                        value = animeSearchResult.attributes?.status ?: NOT_AVAILABLE
                    }
                }
            } else {
                embed {
                    title = "Anime Search"

                    field {
                        name = "Nicht gefunden"
                        value =
                            "Zu deinem gesuchten Begriff wurde entweder nix gefunden oder die Ergebnisse waren nicht Jugendfrei."
                    }
                }
            }
        }
    }

    private const val NOT_AVAILABLE = "Information not available"

    @Serializable
    private data class AnimeSearchResult(
        val attributes: Attributes? = null,
    ) {
        @Serializable
        data class Attributes(
            val titles: Titles? = null,
            val description: String? = null,
            val coverImage: CoverImage? = null,
            val averageRating: String? = null,
            val ageRatingGuide: String? = null,
            val status: String? = null,
        ) {
            @Serializable
            data class Titles(
                val en: String? = null,
                val ja_jp: String? = null,
            )

            @Serializable
            data class CoverImage(
                val original: String? = null,
            )
        }
    }
}
