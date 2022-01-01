package fundamentals.analysisOfAlgorithms

import print

typealias Index = Int
private fun <T: Comparable<T>> binarySearchWithDuplicates(arr: Array<T>, target: T, smallest: Boolean=true): Index {
    return binarySearchWithDuplicates(arr, target, 0, arr.lastIndex, smallest)
}

private fun <T: Comparable<T>> binarySearchWithDuplicates(arr: Array<T>, target: T, lo: Index, hi: Index, smallest: Boolean): Index {
    val mid = (lo + hi) / 2

    return when {
        lo > hi            -> -1
        arr[mid] == target -> findEdge(arr, target, mid, smallest)
        arr[mid] > target  -> binarySearchWithDuplicates(arr, target, lo, mid-1, smallest)
        arr[mid] < target  -> binarySearchWithDuplicates(arr, target, mid+1, hi, smallest)
        else               -> -1
    }
}

private fun <T: Comparable<T>> findEdge(arr: Array<T>, target: T, pos: Index, smallest: Boolean): Index {
    val direction = if (smallest) -1 else 1
    return when {
        pos == 0 || pos == arr.lastIndex -> pos
        arr[pos + direction] == target   -> findEdge(arr, target, pos+direction, smallest)
        else                             -> pos
    }
}

fun main() {
    val arr = Array(10) { it }
    binarySearchWithDuplicates(arr, 2).print()
    binarySearchWithDuplicates(arr, 20).print()

    val arr2 = arrayOf(1,2,3,3,3,3,4,5,6,7,8,9,10)
    binarySearchWithDuplicates(arr2, 3).print() // should be 2
    binarySearchWithDuplicates(arr2,3, false).print() // 5

    val lucky = 7
    val arr3 = Array(10) { lucky }
    binarySearchWithDuplicates(arr3, lucky).print()
    binarySearchWithDuplicates(arr3, lucky, false).print()


}