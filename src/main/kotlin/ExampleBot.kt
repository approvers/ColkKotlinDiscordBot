import net.ayataka.kordis.DiscordClient
import net.ayataka.kordis.Kordis
import net.ayataka.kordis.event.EventHandler
import net.ayataka.kordis.event.events.message.MessageReceiveEvent
import net.ayataka.kordis.event.events.server.ServerReadyEvent
import java.awt.Color

class BotMainClass(private val botToken: String) {

    private val mainChannel: LongArray = longArrayOf(695618239757025290)
    private var helpText: String = """
                        |!k ヘルプ
                        |kotlinの練習用に開発しているBotです
                        |
                        |`!k help`
                        |```
                        |このヘルプを表示します
                        |```
                        |`!k role <userId> <roleName> <colorCode>`
                        |```
                        |<userId>を持つメンバーに<colorCode>の権限<roleName>を与えます
                        |存在しているroleの名前が指定された際には一番最初に見つかったその名前のroleを付与します
                        |```
                        """.trimMargin()
    private var roleText: String = """
                        |実装中です！まっててね
                        |```
                        |`!k role <userId> <roleName> <colorCode>`
                        |```
                        |```
                        |<userId>を持つメンバーに<colorCode>の権限<roleName>を与えます
                        |存在しているroleの名前が指定された際には一番最初に見つかったその名前のroleを付与します
                        |```
                        """.trimMargin()
    private lateinit var client: DiscordClient

    suspend fun run() {
        client = Kordis.create {
            token = this@BotMainClass.botToken
            addListener(this@BotMainClass)
        }
    }

    @EventHandler
    suspend fun onServerReady(event: ServerReadyEvent) {
    }

    @EventHandler
    suspend fun onMessageReceived(event: MessageReceiveEvent) {
        val channel = event.message.serverChannel ?: return
        val author = event.message.author ?: return
        val content:String = if (!(event.message.content.isNullOrBlank())) event.message.content else return
        if (author.bot) return

        println(event.message.content)

        val commands = content.split(" ")

        if (!(commands[0] == "!k")) return

        if (commands[1] == "help") channel.send(helpText)

        if (commands[1] == "role") {
            event.server!!.createRole  { name = commands[3]
                color = Color.decode(commands[4])
                mentionable = true  }
            event.server.
        }
    }
}