package dev.cryztalzone.aurora_prime.app

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.floor
import kotlin.concurrent.thread

class SlashCommands: ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        thread {
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
                    event.reply("https://github.com/Cryztalzone/Aurora-Prime").queue()
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
                "count" -> {
                    val start = event.getOption("start")!!.asInt
                    val end = event.getOption("end")!!.asInt
                    var interval = event.getOption("interval")?.asLong ?: 10
                    if(interval < 2) interval = 2
                    val time = getTime(start, end, interval)

                    event.reply("I will count from $start to $end, this will take approximately $time").queue()
                    var latest: Long

                    var x = start
                    while(x <= end) {
                        latest = event.channel.latestMessageIdLong
                        Thread.sleep(interval * 1000L)
                        val history = event.channel.getHistoryAfter(latest, 100).complete().retrievedHistory.asReversed().filter { m -> !m.author.isBot }
                        if(history.isNotEmpty()) {
                            for (msg in history) {
                                try {
                                    val msgNum = msg.contentRaw.toInt()
                                    if (msgNum == x) x++
                                } catch (e: Exception) {
                                    when(msg.contentRaw.lowercase()) {
                                        "halt" -> {
                                            event.channel.sendMessage("Counting stopped").queue()
                                            return@thread
                                        }
                                        "rem" -> event.channel.sendMessage(getTime(x, end, interval)).queue()
                                        "stop" -> {
                                            event.channel.sendMessage("It's rewind time!\nhttps://www.youtube.com/watch?v=dQw4w9WgXcQ").queue()
                                            x = 1
                                        }
                                    }
                                }
                            }
                        }
                        event.channel.sendMessage(x.toString()).queue()
                        x++
                    }
                }
                "dick" -> {
                    val user = event.getOption("user")?.asUser ?: event.user
                    var size = user.id.takeLast(2).toInt()+1
                    val dick = mutableListOf(user.asMention, " 8")
                    when(user.id) {
                        "237500063029526529" -> {
                            event.reply("Do you want to see ${user.asMention}'s dick?").complete()
                            val latest = event.channel.latestMessageIdLong
                            println(latest)
                            event.channel.addReactionById(latest, "✅").complete()
                            event.channel.addReactionById(latest, "❌").complete()
                            Thread.sleep(15000)
                            val yes = event.channel.retrieveReactionUsersById(latest, "✅").complete().size
                            val no = event.channel.retrieveReactionUsersById(latest, "❌").complete().size

                            if(yes > no) {
                                event.channel.sendMessage("https://i.kym-cdn.com/entries/icons/original/000/033/758/Screen_Shot_2020-04-28_at_12.21.48_PM.png").queue()
                            } else {
                                event.channel.sendMessage("https://i.imgflip.com/498yst.png").queue()
                            }
                        }
                        "571269942993879050" -> {size = 99}
                        "454715683356278784" -> {size = 1}
                    }
                    while(size > 0) {
                        dick.add("=")
                        size--
                    }
                    dick.add("D")
                    if(!event.isAcknowledged) event.reply(dick.joinToString("")).queue()

                }
                "ping" -> {
                    event.reply("Pong! This message took " + ChronoUnit.MILLIS.between(event.timeCreated, OffsetDateTime.now()) + "ms").queue()
                }
            }
        }
    }

    private fun getTime(start: Int, end: Int, interval: Long): String {
        var time = (end - start + 1) * interval
        val days = floor((time / 86400).toDouble()).toInt()
        time -= days * 86400
        val hrs = floor((time / 3600).toDouble()).toInt()
        time -= hrs * 3600
        val mins = floor((time / 60).toDouble()).toInt()
        time -= mins * 60

        return (days.toString() + "D" + hrs + "H" + mins + "M" + time + "S")
    }
}