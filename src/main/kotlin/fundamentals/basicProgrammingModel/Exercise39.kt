package fundamentals.basicProgrammingModel

import print
import java.util.*
import kotlin.math.pow

fun main() {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()

    val N = listOf(3,4,5,6)
    val both = arrayOf(0,0,0,0)

    for (n in N.indices) {
        for (i in 0 until t) {
            val one = Array((10.0).pow(N[n]).toInt()) { randomSixDigitInt() }
            val two = Array((10.0).pow(N[n]).toInt()) { randomSixDigitInt() }

            one.sort()
            two.sort()

            both[n] += one.filter { binarySearch(two, it) > 0 }.count()
        }
    }

    both.toList().map { it / t }.print()

}

fun randomSixDigitInt() = (100_000 until 1_000_000).random()

private fun binarySearch(array: Array<Int>, target: Int, head: Int = 0, tail: Int = array.lastIndex): Int {
    return if (head <= tail) {
        val mid = head + (tail - head) / 2

        when {
            target < array[mid]  -> binarySearch(array, target, head, mid - 1)
            target > array[mid]  -> binarySearch(array, target, mid + 1, tail)
            else                 -> mid
        }

    } else {
        -1
    }
}