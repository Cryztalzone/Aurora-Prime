package Aurora.Prime.app

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands

fun main() {
    val token = System.getenv("bot_token")

    val listeners = SlashCommands()

    val client =
        JDABuilder.createDefault(token)
            .setActivity(Activity.playing("with Kotlin"))
            .addEventListeners(listeners)
            .build()

    client.awaitReady()

    val guild = client.getGuildById(237500464445390849)

    guild!!.updateCommands()
        .addCommands(Commands.slash("echo", "Repeats a message")
            .addOption(OptionType.STRING, "message", "The message to repeat", true))
        .addCommands(Commands.slash("avatar", "Shows the avatar of a user")
            .addOption(OptionType.USER, "user", "The user to show, you if empty", false))
        .addCommands(Commands.slash("delete", "Deletes a channel without triggerung delete-guard")
            .addOption(OptionType.CHANNEL, "channel", "The channel to delete", true)
            .setDefaultEnabled(false))
        .addCommands(Commands.slash("changelog", "Shows the changelog"))
        .addCommands(Commands.slash("rolereaction", "Sends a message where users can react to get a role")
            .addOption(OptionType.STRING, "emoji", "The emoji to use", true)
            .addOption(OptionType.ROLE, "role", "The role to give", true)
            .addOption(OptionType.INTEGER, "inactivity", "The number of days before the role is automatically removed, disabled if empty", false))
        .addCommands(Commands.slash("code", "Reads out a code using TTS")
            .addOption(OptionType.STRING, "code", "The code to read", true))
        .addCommands(Commands.slash("count", "Counts from start to end in a given interval")
            .addOption(OptionType.INTEGER, "start", "The number to start counting from", true)
            .addOption(OptionType.INTEGER, "end", "The number to count to", true)
            .addOption(OptionType.INTEGER, "interval", "The number of seconds between messages, minimum 2, default 10", false))
        .addCommands(Commands.slash("dick", "A special command")
            .addOption(OptionType.USER, "user", "You may select a user, you if empty", false))
        .addCommands(Commands.slash("ping", "How long the bot takes to respond"))
        .addCommands(Commands.slash("say", "Repeats a message using TTS")
            .addOption(OptionType.STRING, "message", "The message to repeat", true))
        .queue()

}