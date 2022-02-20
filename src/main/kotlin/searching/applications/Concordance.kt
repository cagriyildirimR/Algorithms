package searching.applications

import java.io.File
import java.util.*

class Concordance {
    val file = File("src/datasets/tale.txt")
    val words = file.readLines().flatMap { it.split(" ") }

    var st = mutableMapOf<String, MutableSet<Int>>()

    init {
        for (i in words.indices) {
            st[words[i]]?.add(i) ?: st.put(words[i], mutableSetOf(i))
        }
    }
}

fun main() {
    val CONTEXT = 5
    val c = Concordance()

    val scanner = Scanner(System.`in`)
    var word = scanner.next()

    while (word != "exit") {
        val test = c.st[word]
        if (test != null) {
            for (i in test) {
                for (j in -CONTEXT..CONTEXT) {
                    if (j == 0) print("**${c.words[i]}** ") else print("${c.words[i + j]} ")
                }
                println()
            }
        } else {
            println("word is not in dictionary")
        }
        word = scanner.next()
    }
}
