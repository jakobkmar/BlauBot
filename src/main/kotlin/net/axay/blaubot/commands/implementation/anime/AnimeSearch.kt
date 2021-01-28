package net.axay.blaubot.commands.implementation.anime

import com.google.gson.JsonParser
import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.string
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import net.axay.blaubot.commands.api.SlashCommand
import net.axay.blaubot.utils.asJsonObjectOrNull
import net.axay.blaubot.utils.asStringOrNull
import org.apache.commons.lang3.StringUtils
import java.net.URL

const val NOT_AVAILABLE = "Information not available"

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
                coroutineScope {

                    val animeSearchRequest = withContext(Dispatchers.IO) {
                        @Suppress("BlockingMethodInNonBlockingContext") // dispatchers IO
                        JsonParser.parseString(URL("https://kitsu.io/api/edge/anime?filter[text]=$animeName").readText())
                    }.asJsonObject["data"].asJsonArray

                    val information =
                        if (animeSearchRequest.size() > 0) animeSearchRequest[0].asJsonObjectOrNull else null

                    if (information != null) {

                        val attributes = information["attributes"].asJsonObject
                        val titles = attributes["titles"].asJsonObject

                        embed {

                            title = "Anime Search"

                            image = attributes.get("coverImage")?.asJsonObjectOrNull?.get("original")?.asStringOrNull

                            field {
                                name = "Search term"
                                value = animeName
                            }
                            field {
                                name = "Name"
                                value = """
                                    English: ${titles.get("en")?.asStringOrNull ?: NOT_AVAILABLE}
                                    Japanese: ${titles.get("ja_jp")?.asStringOrNull ?: NOT_AVAILABLE}
                                """.trimIndent()
                            }
                            field {
                                name = "Description"
                                value = StringUtils.abbreviate(attributes.get("description")?.asStringOrNull ?: NOT_AVAILABLE, 1024)
                            }
                            field {
                                name = "Rating"
                                value = "${attributes.get("averageRating")?.asStringOrNull ?: NOT_AVAILABLE}%"
                            }
                            field {
                                name = "Age Rating"
                                value = attributes.get("ageRatingGuide")?.asStringOrNull ?: NOT_AVAILABLE
                            }
                            field {
                                name = "Status"
                                value = attributes.get("status")?.asStringOrNull ?: NOT_AVAILABLE
                            }

                        }

                    } else {

                        embed {

                            title = "Anime Search"

                            field {
                                name = "Nicht gefunden"
                                value =
                                    "Zu deinem gesuchten Begriff wurde entweder nix gefunden oder die Ergebnisse waren nicht Jugendfrei"
                            }

                        }

                    }

                }
            }
        }

    }

}
