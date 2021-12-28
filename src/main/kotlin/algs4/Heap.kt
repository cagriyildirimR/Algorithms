package algs4

//object Heap {
//    fun <T: Comparable<T>> sort(pq: Array<T>) {
//        val n = pq.size
//
//
//    }
//
//    private fun <T: Comparable<T>> sink(pq: Array<T>, k: Int, n: Int) {
//        while (2 * k <= n) {
//            var j = 2 * k
//            if(j < n && less(pq, j, j+1)) j++
//            if (!less(pq, k, j)) break
//            exch(pq, k, j)
//            k = j
//        }
//    }
//}