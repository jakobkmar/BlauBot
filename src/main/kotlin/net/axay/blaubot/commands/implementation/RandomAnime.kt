package net.axay.blaubot.commands.implementation

import dev.kord.common.annotation.KordPreview
import dev.kord.core.behavior.interaction.followUp
import dev.kord.core.entity.interaction.CommandInteraction
import dev.kord.core.entity.interaction.InteractionCommand
import dev.kord.rest.builder.interaction.embed
import dev.kord.x.emoji.Emojis
import net.axay.blaubot.commands.api.SlashCommand

@KordPreview
object RandomAnime : SlashCommand(
    "randomanime",
    "Picks a random anime from a predefined list"
) {
    override suspend fun execute(interaction: CommandInteraction, command: InteractionCommand) {
        interaction.acknowledgePublic().followUp {
            embed {
                title = "Der Zufall entscheidet - Welcher Anime?"
                field {
                    val random = animeList.random()
                    name = random
                    value = "Cool, da hat sich der Bot wohl für $random entschieden ${Emojis.slightSmile}! Viel Spaß damit!"
                }
            }
        }
    }

    private val animeList = listOf(
        "2.43: Seiin High School Boys Volleyball Club",
        "22/7",
        "A Silent Voice",
        "Akame ga Kill!",
        "Angels Beats",
        "Angels of Death",
        "Anohana",
        "Arrietty- Die wundersame Welt der Borger",
        "Arte",
        "Assassination Classroom",
        "Attack on Titan",
        "Azur Lane",
        "BNA",
        "Back Arrow",
        "Bakuman",
        "Banana Fish",
        "Beastars",
        "Berserk",
        "Black Bullet",
        "Black Clover",
        "Black Lagon",
        "Bleach",
        "Blue Exorcist",
        "Boruto",
        "Bungou Stray Dogs",
        "Bunny Girl",
        "Cells At Work",
        "Charlotte",
        "Chihiros Reise ins Zauberland",
        "Code Geass: Lelouch of the Rebellion",
        "Cowboy Bebop",
        "Danmachi",
        "Darker Than Black",
        "Darwins Game",
        "Das Königreich der Katzen",
        "Das Schloss im Himmel",
        "Das Wandelnde Schloss",
        "Date A Live",
        "Date a live",
        "Deadman Wonderland",
        "Death Note",
        "Death Parade",
        "Demon Slayer",
        "Der Mohnblumenberg",
        "Detektiv Conan",
        "Devilman Crybaby",
        "Devils' Line",
        "Dia no Ace",
        "Die Chroniken von Erdsee",
        "Die Legende Der Prinzessin Kaguya",
        "Die Walkinder",
        "Digimon",
        "Dororo",
        "Dr Stone",
        "Dragonball",
        "Durarara",
        "Durarara",
        "Erased",
        "Erinnerung an Marnie",
        "Fairy Gone",
        "Fate",
        "Fire Force",
        "Flüstern Des Meeres Ocean Waves",
        "Food Wars! Shokugeki no Soma",
        "From the New World",
        "Fruits Basket",
        "Fullmetal Alchemist: Brotherhood",
        "GOD Eater",
        "Gintama",
        "Gleipnir",
        "Go-Toubun no Hanayome",
        "Goblin Slayer",
        "Goblin Slayer",
        "Golden Time",
        "Golden boy",
        "Gotoubun no Hanayome",
        "Grand Blue",
        "Great Pretender",
        "Great Teacher Onizuka",
        "Gurren Lagann",
        "Haikyu!!",
        "Hataraku Maou-sama",
        "heute und für immer",
        "Hotarobi no Mori e",
        "Hunter × Hunter",
        "ID Invaded",
        "IWGP",
        "Is this a Zombie?",
        "Japan Sinkt 2020",
        "Jibaku Shounen Hanako-kun",
        "JoJo no Kimyō na Bōken",
        "Jujutsu Kaisen",
        "Jujutsu Kaisen",
        "K",
        "Kaguya-sama: Love is War",
        "Kakegurui",
        "Kikisx Kleiner Lieferservice",
        "Kimi no Suizou wo Tabetai",
        "Kono Subarashii Sekai ni Shukufuku o!",
        "Kono Yūsha ga Ore Tsuē Kuse ni Shinchō Sugiru",
        "Kuma Kuma Kuma Bear",
        "Kuroko's Basketball",
        "Listeners",
        "Log Horizon",
        "Made in Abyss",
        "Magi: Sinbad no Bouken",
        "Magi: The Labyrinth of Magic",
        "Magic Kaito",
        "Mein Nachbar Totoro",
        "Mirai Nikki",
        "Mob Psycho 100",
        "Monogatari",
        "My Hero Academia",
        "Nausicaä aus dem Tal der Winde",
        "Neon Genesis Evangelion",
        "No Game No Life",
        "Noragami",
        "One Piece; Naruto",
        "One Punch Man",
        "Oregairu",
        "Oregairu",
        "Overlord",
        "Owari no Seraph",
        "Parasyte the maxim",
        "Plunderer",
        "Pokemon",
        "Pom Poko",
        "Ponyo - Das große Abenteuer am Meer",
        "Porco Rosso",
        "Prinzessin Mononoke",
        "Psycho Pass",
        "Puella Magi Madoka Magica",
        "Rage Of Bahamut",
        "Re:Zero – Starting Life in Another World",
        "Rental Girlfriend (Kanojo, Okarishimasu)",
        "Rokudenashi Majutsu Koushi to Akashic Records",
        "SK8",
        "Saenai Heroine no Sodatekata",
        "Sakura-sou no Pet na Kanojo",
        "Seven Deadly Sins",
        "So im a spider so what?",
        "Soul eater",
        "Steins;Gate",
        "Stimme des Herzens",
        "Sword Art Online",
        "TONIKAWA: Over the Moon for You",
        "Terror in Tokio",
        "That Time i got reincarnated as a slime",
        "The God Of Highschool",
        "The Millionair Detective",
        "The Pet Girl of Sakurasou",
        "The Pet Girl of Sakurasou",
        "The Promised Neverland",
        "The Rising of The Shield Hero",
        "The hidden dungeon only i can enter",
        "Toaru No Index/railgun",
        "Tobaku Mokushiroku Kaiji",
        "Toilend-bound Hanako-kun",
        "Tokyo Ghoul",
        "Toradora",
        "Toradora",
        "Tower of God",
        "Trinity Seven",
        "Tränen der Erinnerung",
        "Um ein Schnurrhaar",
        "Vinland Saga",
        "Violet Evergarden",
        "Weathering with your",
        "Wie der Wind sich hebt",
        "Wotakio: Love is Hard for Otaku",
        "Your Name. – Gestern",
        "Your lie in april",
        "Yu Yu Hakusho",
        "Yu-Gi-Oh!",
        "Yuri on ice",
        "Zankyou no Terror",
    )
}
