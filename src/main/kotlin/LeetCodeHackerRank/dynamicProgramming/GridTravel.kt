package LeetCodeHackerRank.dynamicProgramming

object GridTravel {

    fun naiveGridTravel(n: Long, m: Long): Long {
        if (n == 0L || m == 0L) return 0
        if (n == 1L && m == 1L) return 1
        return naiveGridTravel(n-1, m) + naiveGridTravel(n, m-1)
    }

    fun memoGridTravel(n: Long, m: Long): Long {
        val memo = mutableMapOf<Pair<Long, Long>, Long>()

        fun helper(n: Long, m: Long): Long {
            if (Pair(n,m) in memo) return memo[Pair(n,m)]!!
            if (n == 0L || m == 0L) return 0
            if (n == 1L && m == 1L) return 1
            memo[Pair(n,m)] = helper(n-1, m) + helper(n, m-1)
            memo[Pair(m,n)] = memo[Pair(n,m)]!!
            return memo[Pair(n,m)]!!
        }
        return helper(n,m)
    }
}