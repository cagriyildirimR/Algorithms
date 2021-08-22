package fundamentals.basicProgrammingModel

import print


class Fibonacci(val N: Int) {
    val fibArray = Array<Long?>(N + 1) { null }

    init {
        fibArray[0] = 0L
        fibArray[1] = 1L
    }

    fun fib(n: Int = N): Long {
        if (fibArray[n] == null) fibArray[n] = fib(n - 1) + fib(n - 2)
        return fibArray[n]!!
    }
}

fun main() {
    val f = Fibonacci(500)
    f.fib()
    println(f.fibArray.toList())
}