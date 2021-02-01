package net.axay.blaubot.commands

import dev.kord.common.annotation.KordPreview
import dev.kord.common.entity.string
import dev.kord.core.behavior.followUp
import dev.kord.core.entity.interaction.Interaction
import net.axay.blaubot.commands.api.SlashCommand


@KordPreview
object Legenden : SlashCommand(
    "skin",
    "Zeigt dir den Skin eines beliebigen Spielers",
    {
        subCommand("spieler", "Zeigt dir den Skin eines beliebigen Spielers") {
            string("playerName", "Name des Spielers") {
                required = true
            }
        }
    }
) {

    override suspend fun handleCommand(interaction: Interaction) {
        val player = interaction.command.subCommands["spieler"]?.options?.get("playerName")?.string()
        if (player != null) {
                interaction.acknowledge(true).followUp {
                    embed {
                           title = player
                           image = ("https://minecraft-api.com/api/skins/" + player + "/body/10.8/")
                    }
                }
            }
        }
    }
