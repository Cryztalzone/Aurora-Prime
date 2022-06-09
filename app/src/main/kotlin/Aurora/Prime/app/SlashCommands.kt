package Aurora.Prime.app

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class SlashCommands: ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        when(event.name) {
            "say" -> {
                event.reply(event.getOption("message")!!.asString).setTTS(true).queue()
            }
            "echo" -> {
                event.reply(event.getOption("message")!!.asString).queue()
            }
            "avatar" -> {
                val user = event.getOption("user")?.asUser ?: event.user
                event.reply(user.effectiveAvatarUrl + "?size=1024").queue()
            }
            "delete" -> {
                event.reply("Channel deleted").queue()
                event.getOption("channel")!!.asGuildChannel.delete().queue()
            }
            "changelog" -> {
                event.reply("Changelog coming soon").queue()
            }
            "rolereaction" -> {}
            "code" -> {
                event.deferReply()
                var output = ""
                for(char in event.getOption("code")!!.asString.uppercase()) {
                    output += when(char) {
                        'A' -> "Alpha"
                        'B' -> "Bravo"
                        'C' -> "Charlie"
                        'D' -> "Delta"
                        'E' -> "Echo"
                        'F' -> "Foxtrott"
                        'G' -> "Golf"
                        'H' -> "Hotel"
                        'I' -> "India"
                        'J' -> "Juliet"
                        'K' -> "Kilo"
                        'L' -> "Lima"
                        'M' -> "Mike"
                        'N' -> "November"
                        'O' -> "Oskar"
                        'P' -> "Papa"
                        'Q' -> "Quebec"
                        'R' -> "Romeo"
                        'S' -> "Sierra"
                        'T' -> "Tango"
                        'U' -> "Uniform"
                        'V' -> "Victor"
                        'W' -> "Whiskey"
                        'X' -> "X-Ray"
                        'Y' -> "Yankee"
                        'Z' -> "Zulu"
                        '1' -> "One"
                        '2' -> "Two"
                        '3' -> "Three"
                        '4' -> "Four"
                        '5' -> "Five"
                        '6' -> "Six"
                        '7' -> "Seven"
                        '8' -> "Eight"
                        '9' -> "Nine"
                        '0' -> "Zero"
                        '.' -> "Stop"
                        else -> char
                    }

                    output += ", "
                }

                event.reply(output.dropLast(2)).setTTS(true).queue()
            }
            "count" -> {}
            "dick" -> {}
            "ping" -> {}
        }
    }
}