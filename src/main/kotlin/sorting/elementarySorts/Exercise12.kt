package sorting.elementarySorts

import print
import kotlin.random.Random

private fun <T: Comparable<T>> shellSortAndCount(a: Array<T>): Array<T>{
    val s = a.size
    println("Array has $s elements")
    println("Increment | Compares")
    val gapSequence = generateSequence(1) { it * 3 + 1 }
    val gaps = gapSequence.takeWhile { it <= a.size }.toList().reversed().toTypedArray()
    for (gap in gaps) {
        var numberOfCompares: Int = 0

        for (i in gap until a.size) {
            var j = i - gap

            while (j >= 0 && a[j + gap] < a[j].also { numberOfCompares++ }) {
                a[j+gap] = a[j].also { a[j] = a[j+gap] }
                j -= gap
            }
        }
        println("$gap  ${numberOfCompares.toDouble() / s}")
    }
    println()
    return a
}

fun main() {

    val arraySize = arrayOf(100, 1_000, 10_000, 100_000, 1_000_000)

    for (size in arraySize) {
        val arr =  Array(size) { Random.nextDouble(-1_000_000.0, 1_000_000.0)}
        shellSortAndCount(arr)
    }

}