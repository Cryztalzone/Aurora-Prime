package Aurora.Prime.app

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity

fun main() {
    val builder = JDABuilder.createDefault("NzkxMDU3MTkwODM5OTEwNDMw.X-Jnaw.TnZ3sMwxTIimFZMxy0qZz-Ul1-k")
    builder.setActivity(Activity.watching("you"))
    builder.build()
}
