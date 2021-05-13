package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.commands.converters.string
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import net.axay.blaubot.utils.httpJson
import org.apache.commons.lang3.StringUtils

private const val NOT_AVAILABLE = "Information not available"

@KordPreview
class AnimeSearch(bot: ExtensibleBot) : Extension(bot) {
    override val name = "animesearch_command"

    private class Args : Arguments() {
        val searchTerm by string("searchTerm", "The search input")
    }

    override suspend fun setup() {
        slashCommand(::Args) {
            name = "animesearch"
            description = "Get information about any anime"

            action {
                publicFollowUp {
                    val animeSearchResult = withContext(Dispatchers.IO) {
                        httpJson<Array<AnimeSearchResult>>("https://kitsu.io/api/edge/anime?filter[text]=${arguments.searchTerm}")
                    }.firstOrNull()

                    if (animeSearchResult != null) {
                        embed {
                            title = "Anime Search"

                            image = animeSearchResult.attributes?.coverImage?.original

                            field {
                                name = "Search term"
                                value = arguments.searchTerm
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
        }
    }

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
            val status: String? = null
        ) {
            @Serializable
            data class Titles(
                val en: String? = null,
                val ja_jp: String? = null
            )

            @Serializable
            data class CoverImage(
                val original: String? = null
            )
        }
    }
}
