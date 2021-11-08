package fundamentals.programs

import print
import java.util.*
import kotlin.random.Random

/**
 * Kotlin requires command-line compiler to run similar program
 * This is written so that you to enter required values after you run the program
 */
object RandSeq {

    /**
     * @return array of randomly generated double values
     */
    fun apply(): DoubleArray {
        val scanner = Scanner(System.`in`)
        val n = scanner.nextInt()
        val lo = scanner.nextDouble()
        val hi = scanner.nextDouble()

        return DoubleArray(n) { Random.nextDouble(lo, hi) }
    }
}

fun main() {
    RandSeq.apply().toList().print()
}