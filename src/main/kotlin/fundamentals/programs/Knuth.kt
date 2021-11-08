package fundamentals.programs

import java.util.*
import kotlin.io.path.Path

object Knuth {
    fun <T> shuffle(a:Array<T>) {
        val n = a.size
        for (i in 0 until n) {
            val r = (0..i).random()
            a[r] = a[i].also { a[i] = a[r] }
        }
    }

    fun <T> shuffleAlternate(a:Array<T>) {
        val n = a.size
        for (i in 0 until n) {
            val r = (i until n).random()
            a[r] = a[i].also { a[i] = a[r] }
        }
    }
}

fun main() {
    val cards = Path("src/main/kotlin/dataset/cards.txt")
    val scanner = Scanner(cards)

    while (scanner.hasNext()) {
        val listOfCards = scanner.nextLine().split(" ").toTypedArray()
        Knuth.shuffle(listOfCards)
        println(listOfCards.toList())
    }

}