package fundamentals.programs

import print
import java.util.*

/**
 *
 */

object Average {
    /**
     * Reads n values from standard input and prints their average
     */
    fun apply() {
        val scanner = Scanner(System.`in`)
        val n = scanner.nextInt()
        var sum = 0.0

        for (i in 0 until n) {
            sum += scanner.nextDouble()
        }

        println("Average is ${sum /n}")
    }
}

fun main() {
    Average.apply()
}