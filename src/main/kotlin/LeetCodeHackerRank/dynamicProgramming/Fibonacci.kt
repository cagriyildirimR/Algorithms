package LeetCodeHackerRank.dynamicProgramming

object Fibonacci {

    fun fibonacciNaive(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1
        return fibonacciNaive(n-1) + fibonacciNaive(n-2)
    }

}