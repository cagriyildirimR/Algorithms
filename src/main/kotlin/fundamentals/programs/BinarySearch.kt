package fundamentals.programs

import java.util.*
import kotlin.io.path.Path

//Based on Binary Search implementation in the book Algorithms 4th Ed.

object BinarySearch {

    /**
     * Returns the index of the key in the array a
     *
     * @param a the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    fun indexOf(a: IntArray, key: Int): Int {
        var lo = 0
        var hi = a.size - 1
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            if      (key < a[mid]) hi = mid - 1
            else if (key > a[mid]) lo = mid + 1
            else return mid
        }
        return -1
    }
}

fun main() {
    val path = Path("src/main/kotlin/dataset/tinyT.txt")
    val scanner = Scanner(path)

    val list = mutableListOf<Int>()

    while (scanner.hasNext()) {
        list.add(scanner.nextInt())
    }

    val sortedList = list.sorted()
    println(sortedList)

    println("Index of key:13 is ${BinarySearch.indexOf(sortedList.toIntArray(), 13)}")
    println("Index of key:77 is ${BinarySearch.indexOf(sortedList.toIntArray(), 77)}")
    println("Index of key:99 is ${BinarySearch.indexOf(sortedList.toIntArray(), 99)}")
}
