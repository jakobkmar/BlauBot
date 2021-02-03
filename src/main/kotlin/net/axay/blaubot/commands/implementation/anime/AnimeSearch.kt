package net.axay.blaubot.commands.implementation.anime

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.string
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import net.axay.blaubot.commands.api.SlashCommand
import net.axay.blaubot.utils.httpJson
import org.apache.commons.lang3.StringUtils

const val NOT_AVAILABLE = "Information not available"

@Serializable
class AnimeSearchResult(
    val data: Array<Data>
) {
    @Serializable
    data class Data(
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

@KordPreview
object AnimeSearch : SlashCommand(
    "animesearch",
    "Get information about any anime",
    {
        string("name", "The name of the anime") {
            required = true
        }
    }
) {

    override suspend fun handleCommand(interaction: Interaction) {

        val animeName = interaction.command.options["name"]?.string()
        if (animeName != null) {
            interaction.acknowledge(true).followUp {

                val animeSearchResult = withContext(Dispatchers.IO) {
                    httpJson<AnimeSearchResult>("https://kitsu.io/api/edge/anime?filter[text]=$animeName")
                }.data.firstOrNull()

                if (animeSearchResult != null) {

                    embed {

                        title = "Anime Search"

                        image = animeSearchResult.attributes?.coverImage?.original

                        field {
                            name = "Search term"
                            value = animeName
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
