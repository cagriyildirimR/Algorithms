package fundamentals.analysisOfAlgorithms

import print

//Bitonic search

private fun <T: Comparable<T>> binarySearch(arr: Array<T>, target: T): Boolean {
    return binarySearch(arr, 0, arr.lastIndex, target)
}

private fun <T: Comparable<T>> binarySearch(arr: Array<T>, lo: Int, hi: Int, target: T): Boolean {
    val mid = (hi + lo) / 2
    return when {
        hi < lo            -> false
        arr[mid] == target -> true
        arr[mid] > target  -> binarySearch(arr, lo, mid-1, target)
        else               -> binarySearch(arr, mid+1, hi, target)
    }
}

private fun <T: Comparable<T>> binarySearchTop(arr: Array<T>, lo: Int, hi: Int): Int {
    val mid = (hi + lo) / 2
    return when {
        arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1] -> mid
        arr[mid] < arr[mid-1]                          -> binarySearchTop(arr, lo, mid-1)
        else                                           -> binarySearchTop(arr, mid+1, hi)
    }
}


private fun <T: Comparable<T>> binarySearchReversed(arr: Array<T>, lo: Int, hi: Int, target: T): Boolean {
    val mid = (hi + lo) / 2
    return when {
        hi < lo            -> false
        arr[mid] == target -> true
        arr[mid] < target  -> binarySearch(arr, lo, mid-1, target)
        else               -> binarySearch(arr, mid+1, hi, target)
    }
}

private fun <T: Comparable<T>> bitonicSearch(arr: Array<T>, target: T): Boolean {
    val top = binarySearchTop(arr,0, arr.lastIndex)
    return binarySearch(arr, 0, top, target) || binarySearchReversed(arr, top, arr.lastIndex, target)
}

fun main() {
    val arr = Array(10) { it }
    binarySearch(arr, 3).print()
    binarySearch(arr, -1).print()
    binarySearch(arr, 999).print()

    val arrT = arrayOf(1,3,5,7,9,11,10,8,6)
    binarySearchTop(arrT, 0 ,arrT.lastIndex).print() // should be 5

    val size = 100
    val randomIndex = (2 until size-2).random()
    val randomBitonicArray = Array(size) {if (it < randomIndex) it else size + randomIndex - it - 1}
    println("${binarySearchTop(randomBitonicArray, 0, randomBitonicArray.lastIndex)} should be $randomIndex")

    bitonicSearch(randomBitonicArray, 7).print()
    bitonicSearch(randomBitonicArray, 7777).print()
    bitonicSearch(randomBitonicArray, -7777).print()


}
