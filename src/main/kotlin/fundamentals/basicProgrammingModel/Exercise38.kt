package fundamentals.basicProgrammingModel

import java.util.*
import kotlin.io.path.Path
import kotlin.system.measureNanoTime

private fun bruteSearch(key: Int, a: Array<Int>): Int {
    for (i in a.indices) {
        if (a[i] == key) return i
    }
    return -1
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

fun main() {

    val key = 528476

    val filePath = Path("C:\\Users\\CAGRI\\IdeaProjects\\Algorithms\\src\\fundamentals.basicProgrammingModel.main\\resources\\largeW.txt")
    val scanner = Scanner(filePath)
    val list = Array(1_000_000) { 0 }

    var i = 0
    while (scanner.hasNext()) {
        list[i] = scanner.nextInt()
        i++
    }

    list.sort()

    val timeForBrute = measureNanoTime  {
        bruteSearch(key,list)
    }

    println("Time elapsed for brute force is: $timeForBrute")

    val timeForBinarySearch = measureNanoTime {
        binarySearch(list,key).also(::println)
    }

    println("Time elapsed for binary search is: $timeForBinarySearch")

    val timeForBinarySearch2 = measureNanoTime {
        binarySearch(list,key).also(::println)
    }

}

