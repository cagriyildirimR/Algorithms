package algs4

object Merge {

    /**
     * Merges a\[lo..mid\] with a\[mid+1, hi\] using [aux\[lo..hi\]]
     */
    private fun <T: Comparable<T>> merge(a: Array<T>, aux: Array<T>, lo: Int, mid: Int, hi: Int) {
        // precondition a[lo..mid] and a[mid+1..hi] are sorted arrays
        assert(isSorted(a,lo, mid))
        assert(isSorted(a,mid+1, hi))

        //copy to aux[]
        for (k in lo..hi) {
            aux[k] = a[k]
        }

        // merge back to a[]
        var i = lo
        var j = mid+1

        for (k in lo..hi) {
            when {
                i > mid              -> a[k] = aux[j++]
                j > hi               -> a[k] = aux[i++]
                less(aux[j], aux[i]) -> a[k] = aux[j++]
                else                 -> a[k] = aux[i++]
            }
        }

        // postcondition: a[lo..hi] is sorted
        assert(isSorted(a, lo, hi))
    }

    private fun <T: Comparable<T>> sort(a: Array<T>, aux: Array<T>, lo: Int, hi: Int) {
        if (hi<=lo) return //exit
        var mid = lo + (hi - lo) / 2
        sort(a, aux, lo, mid)
        sort(a, aux, mid + 1, hi)
        merge(a, aux, lo, mid, hi)
    }

    /**
     * Rearranges the array in ascending order, using the natural order
     * @param a the array to be sorted
     */
    fun <T: Comparable<T>> sort(a: Array<T>) {
        val aux = a.copyOf()
        sort(a, aux,0, a.lastIndex)
        assert(isSorted(a))
    }


    /**
     * Checks if v < w
     */
    private fun <T: Comparable<T>> less(v: T, w: T): Boolean {
        return v < w
    }

    private fun <T: Comparable<T>> isSorted(a: Array<T>, lo: Int, hi: Int): Boolean {
        for (i in lo+1..hi) {
            if (less(a[i], a[i-1])) return false
        }
        return true
    }

    private fun <T: Comparable<T>> isSorted(a: Array<T>): Boolean {
        return isSorted(a, 0, a.lastIndex)
    }
}

fun main() {
    val a = Array(10) { (1..100).random() }
    println("Unsorted")
    println(a.toList())
    Merge.sort(a)
    println("Sorted")
    println(a.toList())
}