package sorting.elementarySorts

import print

private fun <T: Comparable<T>> shellSort(a: Array<T>): Array<T>{
    val gapSequence = generateSequence(1) { it * 3 + 1 }
    val gaps = gapSequence.takeWhile { it <= a.size }.toList().reversed().toTypedArray()
    for (gap in gaps) {
        for (i in gap until a.size) {
            var j = i - gap

            while (j >= 0 && a[j + gap] < a[j]) {
                a[j+gap] = a[j].also { a[j] = a[j+gap] }
                j -= gap
            }
        }
    }
    return a
}

fun main() {
    val arr = Array(5) { Array(20) { (-100..100).random() } }

    for (a in arr) {
        shellSort(a)
        println(a.joinToString(", "))
    }
}