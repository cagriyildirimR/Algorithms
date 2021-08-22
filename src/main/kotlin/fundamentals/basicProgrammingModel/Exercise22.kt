package fundamentals.basicProgrammingModel

fun main() {
    val a = Array<Int>(20) { it }
    binarySearch(a,5).also(::println)

}

private fun binarySearch(array: Array<Int>, target: Int, depth: Int = 0, lo: Int = 0, hi: Int = array.lastIndex): Int {
    return if (lo <= hi) {
        val mid = lo + (hi - lo) / 2
        println("lo: $lo | hi: $hi | depth: $depth")

        when {
            target < array[mid]  -> binarySearch(array, target, depth + 1, lo, mid - 1)
            target > array[mid]  -> binarySearch(array, target, depth + 1, mid + 1, hi)
            else                 -> mid
        }

    } else {
        -1
    }
}