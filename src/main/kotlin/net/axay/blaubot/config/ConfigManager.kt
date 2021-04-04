package net.axay.blaubot.config

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import net.axay.blaubot.config.data.DiscordApplication
import java.io.File

object ConfigManager {
    class ConfigFile(path: String) : File(path) {
        init {
            if (!parentFile.exists()) parentFile.mkdirs()
            if (!exists()) createNewFile()
        }
    }

    val discordApplication =
        ConfigFactory.parseFile(ConfigFile("./config/discordApplication.conf")).extract<DiscordApplication>()
}
