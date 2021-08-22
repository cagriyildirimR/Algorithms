package fundamentals.basicProgrammingModel

import print

fun main() {
    val a = arrayOf(1,2,2,2,2,2,2,3,3,4,5,6,6,7,8,8,9)
    binarySearch(a,2).print()

    rank(2,a).print()
    count(2,a).print()
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


private fun rank(key: Int, arr: Array<Int>): Int {
    var i = binarySearch(arr,key)

    if (i != -1) {
        while (arr[i - 1] == arr[i]) i--
    } else {
        throw Exception("Value is not in array")
    }
    return i
}

private fun count(key: Int, arr: Array<Int>): Int {
    //return arr.filter { it == key }.count()
    var i = rank(key,arr)
    var count = 1
    while (arr[i] == arr[i+1]) {
        i++
        count++
    }
    return count
}