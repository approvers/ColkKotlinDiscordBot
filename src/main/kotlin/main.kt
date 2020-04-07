import kotlinx.coroutines.runBlocking
import java.io.File

fun main (args : Array<String>) = runBlocking {
    val token = System.getenv("TOKEN")
    println(token)
    BotMainClass(token).run()
}