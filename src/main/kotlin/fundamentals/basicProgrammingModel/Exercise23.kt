package fundamentals.basicProgrammingModel

import java.io.File
import java.util.*


fun main() {
    val scanner = Scanner(System.`in`)
    val filePath = scanner.next()
    val operator = scanner.next()

    val input = mutableListOf<Int>()

    while (scanner.hasNextInt()) input.add(scanner.nextInt())

    val whitelist: List<Int> = File(filePath).readLines().map { it.toInt() }
    if (operator !in "+-") throw Exception("operator can be either + or -")

    when (operator) {
        "+"  -> input.filter { binarySearch(whitelist.toTypedArray(),it) == -1 }.also(::println)
        else -> input.filter { binarySearch(whitelist.toTypedArray(),it) != -1 }.also(::println)
    }

}

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

