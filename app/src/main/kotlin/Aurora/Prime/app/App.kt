package Aurora.Prime.app

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity

fun main() {
    val token = System.getenv("bot_token")

    val builder = JDABuilder.createDefault(token)
    builder.setActivity(Activity.watching("you"))
    builder.build()
}