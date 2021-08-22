package fundamentals.basicProgrammingModel

import printArray
import java.util.*

private fun shuffle(a: Array<Int>): Array<Int> {
    val N = a.size - 1
    for (i in 0..N) {
        val r = i + (0..N - i).random()
        a[i] = a[r].also { a[r] = a[i] }
    }
    return a
}

fun main() {
    shuffleTest()
}

private fun shuffleTest() {
    val scanner = Scanner(System.`in`)
    val m = scanner.nextInt()
    val n = scanner.nextInt()

    val result = Array (m) { Array(m) { 0 } }

    for (i in 0 until n) {
        val a = Array(m) { it }
        shuffle(a).forEachIndexed { inx, v -> result[v][inx]++ }
    }

    printArray(result)
}