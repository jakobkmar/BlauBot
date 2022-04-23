package bot.repoapi.mojang

import io.ktor.client.*
import bot.repoapi.mojang.model.VersionManifest

class LauncherMetaApi(
    override val client: HttpClient,
    override val apiUrl: String = "https://launchermeta.mojang.com/mc",
) : bot.repoapi.AbstractRepositoryApi() {

    suspend fun getVersionManifest() =
        repoRequest<VersionManifest>("/game/version_manifest_v2.json")
}
