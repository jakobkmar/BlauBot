package net.axay.blaubot.commands.implementation

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.commands.slash.converters.stringChoice
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview
import net.axay.blaubot.commands.api.testGuild

private val otakuTerms = mapOf(
    "Senpai" to "Wird direkt in \"Senior\" übersetzt, bezieht sich aber technisch auf jemanden, der mehr Erfahrung als du in Bezug auf Beschäftigung, Schule oder etwas anderes hat.",
    "Kohai" to "Dieses Wort bedeutet \"Junior\", aber technisch gesehen bezieht es sich auf Personen, die in einem bestimmten Aspekt weniger Erfahrung mit dir haben, wie Arbeit, Schule, vielleicht sogar Sport!",
    "Oneesan" to "Dies wird verwendet, um sich auf deine „ältere Schwester“ zu beziehen, kann aber auch verwendet werden, um sich auf ein Mädchen / eine Frau zu beziehen, die etwas älter ist als Sie im Allgemeinen, wenn sie in einer informellen Umgebung verwendet wird.",
    "Oniisan" to "In Anime und Manga bedeutet dieses Wort \"älterer Bruder\", den du für einen echten älteren Bruder nur für einen älteren (aber noch jungen) Jungen oder Mann verwenden kannst.",
    "Sensei" to "Dies bedeutet direkt \"Lehrer\".",
    "Waifu" to "Aus dem englischen Wort \"Frau\" entlehnt, bezieht sich dies auf einen Anime / Manga / Spiel / Charakter oder sogar ein Idol, dem eine Person gegenüber Zuneigung empfindet.",
    "Mangaka" to "Dies ist, was du eine Person nennst, die Manga erstellen. (Autoren)",
    "Seiyuu" to "Dies bezieht sich auf Sprecher, die Anime Charaktere sprechen.",
    "Kawaii" to "Eines der am häufigsten verwendeten Wörter in japanischen Medien, Kawaii, bedeutet süß oder bezaubernd.",
    "Baka oder aho" to "Baka und Aho bedeuten beide \"dumm / idiot\" und sind auch ein in japanischen Medien übliches Wort.",
)

@KordPreview
class AnimeTerms(bot: ExtensibleBot) : Extension(bot) {
    override val name = "animeterms_command"

    private class Args : Arguments() {
        val term by stringChoice("term", "The term you would like to have explained", otakuTerms.keys.map { it to it }.toMap())
    }

    override suspend fun setup() {
        slashCommand(::Args) {
            testGuild()

            name = "anime"
            description = "Explains otaku terms to you"

            action {
                publicFollowUp {
                    embed {
                        title = "Anime Begriffsinformation"
                        field {
                            name = arguments.term
                            value = otakuTerms[arguments.term].toString()
                        }
                    }
                }
            }
        }
    }
}

// disabled:

/*
"Tsundere" to "Dies ist ein Charakter / eine Person, die normalerweise eine kalte und harte Persönlichkeit von außen hat, aber tatsächlich von innen sehr liebevoll und warm ist.",
"Yandere" to "Dies ist ein Charakter / eine Person, die wirklich süß und nett beginnt, aber am Ende sehr besessen und sogar gewalttätig ist. Wirklich beängstigend.",
"Yuri" to "Als Japaner Slang Wort für „Mädchen Liebe”Bezieht sich dieses Wort auf Mädchen in einer lesbischen Beziehung. Dies bezieht sich auch auf eine Kategorie von Anime und Manga, die sich um romantische weibliche Beziehungen dreht.",
"Yaoi" to "Das ist ein Japaner Slang Wort für „Jungs Liebe”. Dieses Wort bezieht sich auch auf eine Kategorie von Anime und Manga, die sich um schwule Beziehungen dreht.",
"Fan Service" to "Dies sind Szenen in Anime / Manga / Serien, die speziell dafür entwickelt wurden, Fans zu erfreuen oder zu necken. Dies kann populäre Charaktere mit ein wenig Nacktheit oder anderen Outfits beinhalten.",
"Harem" to "Dies ist eine Anime / Manga-Geschichte, die sich um einen Typen dreht, der von der Hauptfigur umgeben und irgendwie romantisch verwickelt ist.",
"Ecchi" to "Dies ist die Handlung oder ein Wort, das sexuelle Themen und sogar Nacktheit beinhaltet, außer es ist nicht so explizit wie Hentai.",
"Hentai" to "Dies ist ein Genre aus Anime und Manga, das sexuelle Auswirkungen hat. Um ehrlich zu sein, das ist technisch gesehen Anime / Manga-Porno. Wir brauchen einen Manga/Anime Command, damit jeder den Unterschied kennt."
"Bishounen oder Bishie" to "Sehr gut aussehender Mann. Dies kann sowohl für reale Personen als auch für fiktive Charaktere verwendet werden.",
"Bishoujo oder Bijin" to "Mit diesen Begriffen werden sehr gut aussehende Frauen bezeichnet, die für echte Menschen und fiktive Charaktere verwendet werden.",
"Chibi" to "Etwas, das klein und sehr liebenswert ist.",
*/
