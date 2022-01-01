package fundamentals.analysisOfAlgorithms

/** local minimum in array such that
 *  a[n] < a[n-1] and a[n] < a[n+1]
 *  O(2lg(n))
 */

// [1, 2, 3 ,10 ,11, 6, 5]

private fun localMinimum(arr: Array<Int>): Int {
//    for (i in 1 until arr.lastIndex) {
//        if (arr[i] < arr[i-1] && arr[i]<arr[i+1]) return arr[i] // 2*N
//    }

    // [ x, y, z, 9, 10, 11, 12, 13, 14]
    // right-hand side of 10 may or may not contain local minima
    // left-hand side has two possibilities
    //      one is it keeps decreasing in descending order that means the first element of array is the local minima
    //      otherwise one of the value is bigger than it's right neighbour

    return localMinimum(arr, 0, arr.lastIndex)
}

private fun localMinimum(arr: Array<Int>, lo: Int, hi: Int): Int {
    val mid = (hi + lo) / 2
    val li = arr.lastIndex
    return when {
        lo == hi -> arr[lo]
        arr[0] < arr[1] -> arr[0]
        arr[li] < arr[li-1] -> arr[li]
        arr[mid] > arr[mid+1] -> localMinimum(arr, mid+1, hi)
        arr[mid] > arr[mid-1] -> localMinimum(arr, lo, mid-1)
        else -> arr[mid]
    }
}

fun main() {
    val sizeOfArray = 10
    val orderedArray = Array<Int>(sizeOfArray) { it }
    val localMinimumOfOrderedArray = localMinimum(orderedArray)
    println("local minima of ordered array ${orderedArray.toList()} is $localMinimumOfOrderedArray")
    assert(0==localMinimumOfOrderedArray)

    val descendingArray = orderedArray.reversedArray()
    val localMinimumOfDescendingArray = localMinimum(descendingArray)
    println("local minima of descending array ${descendingArray.toList()} is $localMinimumOfDescendingArray")
    assert(localMinimumOfDescendingArray == 0)

    val arr = Array(sizeOfArray) {if (it <= 3) 3 - it else it }
    println("local minima of array ${arr.toList()} is ${localMinimum(arr)}")
}
