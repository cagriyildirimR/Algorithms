package LeetCodeHackerRank.dynamicProgramming

object Fibonacci {

    fun naiveFibonacci(n: Int): Long {
        if (n == 0) return 0
        if (n == 1) return 1
        return naiveFibonacci(n - 1) + naiveFibonacci(n - 2)
    }

    fun memoFibonacci(n: Int): Long {
        val memo = Array<Long>(n + 1) { 0L }

        fun helper(n: Int): Long {
            if (memo[n] != 0L) return memo[n]
            if (n == 0) return 0L
            if (n == 1) return 1L
            memo[n] = helper(n - 1) + helper(n - 2)
            return memo[n]
        }
        return helper(n)
    }

}